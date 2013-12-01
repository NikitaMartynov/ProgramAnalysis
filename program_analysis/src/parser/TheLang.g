grammar TheLang;

options {
  language = Java;
  backtrack=true;
}

@header {
package parser;

import ast.*;
import ast.arith.*;
import ast.bool.*;
import ast.statement.*;
import ast.declaration.*;
}

@lexer::header {
package parser;

}

program returns [Program value]
    : 'program' ( d=declaration s=statement 'end' EOF { $value = new Program(d,s); }
    		| s=statement 'end' EOF { $value = new Program(s); } )
    ;
    
// variable declarations
declaration returns [Declaration value]
    : d=base_declaration   { $value = d; }	
      ( d=base_declaration { $value = new SeqDeclaration($value,d); } )*
    ;
    
base_declaration returns[Declaration value]
    : (l = level
      'int' IDENTIFIER (';'
      { $value = new VariableDeclaration(l,$IDENTIFIER.getText()); }//default: set 0 for new variables
      |'[' INTEGER ']' ';' 
      { $value = new ArrayDeclaration(l,$IDENTIFIER.getText(), Integer.parseInt($INTEGER.getText()));}
      ))
      |
      'int' IDENTIFIER (';'
      { $value = new VariableDeclaration($IDENTIFIER.getText()); }//default: set 0 for new variables
      |'[' INTEGER ']' ';' 
      { $value = new ArrayDeclaration($IDENTIFIER.getText(), Integer.parseInt($INTEGER.getText()));}
      )
    ;

level returns [SecurityLevel value]
	: 'low' {$value = new SecurityLevelLow();}
	| 'high' {$value = new SecurityLevelHigh();};

statement returns [Statement value]
    : s=base_statement       { $value = s; }
      ( s=base_statement { $value = new SeqStatement($value,s); } )*
    ;

base_statement returns [Statement value]
    : s = assignStmt	{ $value = s; }
    | s = skipStmt	{ $value = s; }
    | s = readStmt	{ $value = s; }
    | s = writeStmt	{ $value = s; }
    | s = ifStmt	{ $value = s; }
    | s = whileStmt	{ $value = s; }
    ;
    
assignStmt returns [Statement value]
	: IDENTIFIER (':=' e=arith_expr ';'
	{ $value = new AssignStatement($IDENTIFIER.getText(), e); }
	| '[' e1 = arith_expr ']'':=' e2=arith_expr ';'
	{ $value = new ArrayAssignStatement($IDENTIFIER.getText(), e1, e2); })
	;
skipStmt returns [Statement value]
	: 'skip' ';'                                  
	{ $value = new SkipStatement(); }
	;

readStmt returns [Statement value]
	: 'read' IDENTIFIER( ';'
	{ $value = new ReadStatement($IDENTIFIER.getText());}
	| '[' e = arith_expr ']' ';'
	{ $value = new ReadArrayStatement($IDENTIFIER.getText(),e);}
	)
	;
	
writeStmt returns [Statement value]
	: 'write' e = arith_expr ';'
	{ $value = new WriteStatement(e);}
	;
ifStmt returns [Statement value]
	: 'if' c=bool_expr 'then' s1=statement
	  'else' s2=statement 'fi'                 
	{ $value = new IfStatement(c,s1,s2); }
	;
whileStmt returns [Statement value]
	: 'while' c=bool_expr 'do' s=statement 'od' 
	{ $value = new WhileStatement(c,s); }
	;

// bool expressions
bool_expr returns [BoolExpr value]
	: e=mid_bool_expr	{ $value = e; }
	( '|' e=mid_bool_expr	{ $value = new OrExpr($value,e); }
	)* 
	;

mid_bool_expr returns [BoolExpr value]
	: e = base_bool_expr	{ $value = e; }
	('&' e = base_bool_expr	{ $value = new AndExpr($value,e); }
	)*
	;

base_bool_expr returns [BoolExpr value]
    : b = not_bool_expr		{ $value = b;}
    | b = paren_bool_expr 	{ $value = b; }
    | 'true'                    { $value = new BoolValueExpr(true); }
    | 'false'                   { $value = new BoolValueExpr(false); }
    | e1=arith_expr ( '=' e2=arith_expr  { $value = new EqualsExpr(e1,e2); }//arith_expr might contain parentheses
                    | '<=' e2=arith_expr { $value = new LessThanEqualsExpr(e1,e2); } 
                    | '<' e2=arith_expr  { $value = new LessThanExpr(e1,e2); } 
                    | '>=' e2=arith_expr { $value = new GreaterThanEqualsExpr(e1,e2); } 
                    | '>' e2=arith_expr  { $value = new GreaterThanExpr(e1,e2); } 
                    | '!=' e2=arith_expr { $value = new NotEqualsExpr(e1,e2); } ) 
     ;
    
not_bool_expr returns [BoolExpr value]
	: '!' e = bool_expr 
	{ $value = new NotExpr(e); }
	;
	
paren_bool_expr returns [BoolExpr value]
    : '(' e=bool_expr ')' {$value = e;}
    ;

// arithmethic expressions
arith_expr returns [ArithExpr value]
    : e=mult_div_arith_expr       { $value = e; }
      ( '+' e=mult_div_arith_expr { $value = new PlusExpr($value,e); } 
      | '-' e=mult_div_arith_expr { $value = new MinusExpr($value,e); } )*
    ;

mult_div_arith_expr returns [ArithExpr value]
    : e=un_min_arith_expr       { $value = e; }
      ( '*' e=un_min_arith_expr { $value = new MultExpr($value,e); } 
      | '/' e=un_min_arith_expr { $value = new DivExpr($value,e); }) *
    ;
    
un_min_arith_expr returns [ ArithExpr value]
    : e=base_arith_expr    { $value = e; }
    |'-' e=base_arith_expr { $value = new UnMinExpr(e); }
    ;	

base_arith_expr returns [ArithExpr value]
    : INTEGER 		{ $value = new NumExpr(Integer.parseInt($INTEGER.getText())); }
    | IDENTIFIER  	{ $value = new IdExpr($IDENTIFIER.getText()); }
    | e=array_arith_expr { $value = e; }
    | e=paren_arith_expr { $value = e; }
    ;
    
array_arith_expr returns [ArithExpr value]
    : IDENTIFIER '[' e=arith_expr ']' {$value = new ArrayExpr($IDENTIFIER.getText(), e);} 
    ;
    
paren_arith_expr returns [ArithExpr value]
    : '(' e=arith_expr ')' {$value = e;}
    ;
    


COMMENT : '(*' (options {greedy=false;} : .)* '*)' {$channel=HIDDEN;}
     ;

INTEGER : ('0' | '1'..'9' '0'..'9'*);

IDENTIFIER : LETTER (LETTER|'0'..'9')* ;

fragment
LETTER : 'A'..'Z'
       | 'a'..'z'
       | '_'
       ;

WS : (' '|'\r'|'\t'|'\u000C'|'\n') { skip(); } ;
