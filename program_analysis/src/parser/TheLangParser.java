// $ANTLR 3.3 Nov 30, 2010 12:45:30 E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g 2013-10-26 20:07:34

package parser;

import ast.*;
import ast.arith.*;
import ast.bool.*;
import ast.statement.*;
import ast.declaration.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class TheLangParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER", "INTEGER", "COMMENT", "LETTER", "WS", "'program'", "'end'", "'int'", "';'", "'['", "']'", "'low'", "'high'", "':='", "'skip'", "'read'", "'write'", "'if'", "'then'", "'else'", "'fi'", "'while'", "'do'", "'od'", "'|'", "'&'", "'true'", "'false'", "'='", "'<='", "'<'", "'>='", "'>'", "'!='", "'!'", "'('", "')'", "'+'", "'-'", "'*'", "'/'"
    };
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__10=10;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int IDENTIFIER=4;
    public static final int INTEGER=5;
    public static final int COMMENT=6;
    public static final int LETTER=7;
    public static final int WS=8;

    // delegates
    // delegators


        public TheLangParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TheLangParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TheLangParser.tokenNames; }
    public String getGrammarFileName() { return "E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g"; }



    // $ANTLR start "program"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:23:1: program returns [Program value] : 'program' (d= declaration s= statement 'end' EOF | s= statement 'end' EOF ) ;
    public final Program program() throws RecognitionException {
        Program value = null;

        Declaration d = null;

        Statement s = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:24:5: ( 'program' (d= declaration s= statement 'end' EOF | s= statement 'end' EOF ) )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:24:7: 'program' (d= declaration s= statement 'end' EOF | s= statement 'end' EOF )
            {
            match(input,9,FOLLOW_9_in_program54); if (state.failed) return value;
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:24:17: (d= declaration s= statement 'end' EOF | s= statement 'end' EOF )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==11||(LA1_0>=15 && LA1_0<=16)) ) {
                alt1=1;
            }
            else if ( (LA1_0==IDENTIFIER||(LA1_0>=18 && LA1_0<=21)||LA1_0==25) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:24:19: d= declaration s= statement 'end' EOF
                    {
                    pushFollow(FOLLOW_declaration_in_program60);
                    d=declaration();

                    state._fsp--;
                    if (state.failed) return value;
                    pushFollow(FOLLOW_statement_in_program64);
                    s=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,10,FOLLOW_10_in_program66); if (state.failed) return value;
                    match(input,EOF,FOLLOW_EOF_in_program68); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new Program(d,s); 
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:25:9: s= statement 'end' EOF
                    {
                    pushFollow(FOLLOW_statement_in_program82);
                    s=statement();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,10,FOLLOW_10_in_program84); if (state.failed) return value;
                    match(input,EOF,FOLLOW_EOF_in_program86); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new Program(s); 
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "program"


    // $ANTLR start "declaration"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:29:1: declaration returns [Declaration value] : d= base_declaration (d= base_declaration )* ;
    public final Declaration declaration() throws RecognitionException {
        Declaration value = null;

        Declaration d = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:30:5: (d= base_declaration (d= base_declaration )* )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:30:7: d= base_declaration (d= base_declaration )*
            {
            pushFollow(FOLLOW_base_declaration_in_declaration118);
            d=base_declaration();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = d; 
            }
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:31:7: (d= base_declaration )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11||(LA2_0>=15 && LA2_0<=16)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:31:9: d= base_declaration
            	    {
            	    pushFollow(FOLLOW_base_declaration_in_declaration135);
            	    d=base_declaration();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new SeqDeclaration(value,d); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "declaration"


    // $ANTLR start "base_declaration"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:34:1: base_declaration returns [Declaration value] : ( level )? 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) ;
    public final Declaration base_declaration() throws RecognitionException {
        Declaration value = null;

        Token IDENTIFIER1=null;
        Token INTEGER2=null;

        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:35:5: ( ( level )? 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:35:7: ( level )? 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' )
            {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:35:7: ( level )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=15 && LA3_0<=16)) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:0:0: level
                    {
                    pushFollow(FOLLOW_level_in_base_declaration164);
                    level();

                    state._fsp--;
                    if (state.failed) return value;

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_base_declaration174); if (state.failed) return value;
            IDENTIFIER1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_base_declaration176); if (state.failed) return value;
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:36:24: ( ';' | '[' INTEGER ']' ';' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            else if ( (LA4_0==13) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:36:25: ';'
                    {
                    match(input,12,FOLLOW_12_in_base_declaration179); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new VariableDeclaration(IDENTIFIER1.getText()); 
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:38:8: '[' INTEGER ']' ';'
                    {
                    match(input,13,FOLLOW_13_in_base_declaration196); if (state.failed) return value;
                    INTEGER2=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_base_declaration198); if (state.failed) return value;
                    match(input,14,FOLLOW_14_in_base_declaration200); if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_base_declaration202); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ArrayDeclaration(IDENTIFIER1.getText(), Integer.parseInt(INTEGER2.getText()));
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "base_declaration"


    // $ANTLR start "level"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:43:1: level : ( 'low' | 'high' );
    public final void level() throws RecognitionException {
        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:43:7: ( 'low' | 'high' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:
            {
            if ( (input.LA(1)>=15 && input.LA(1)<=16) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "level"


    // $ANTLR start "statement"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:45:1: statement returns [Statement value] : s= base_statement (s= base_statement )* ;
    public final Statement statement() throws RecognitionException {
        Statement value = null;

        Statement s = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:46:5: (s= base_statement (s= base_statement )* )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:46:7: s= base_statement (s= base_statement )*
            {
            pushFollow(FOLLOW_base_statement_in_statement255);
            s=base_statement();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = s; 
            }
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:47:7: (s= base_statement )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==IDENTIFIER||(LA5_0>=18 && LA5_0<=21)||LA5_0==25) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:47:9: s= base_statement
            	    {
            	    pushFollow(FOLLOW_base_statement_in_statement275);
            	    s=base_statement();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new SeqStatement(value,s); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "statement"


    // $ANTLR start "base_statement"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:50:1: base_statement returns [Statement value] : (s= assignStmt | s= skipStmt | s= readStmt | s= writeStmt | s= ifStmt | s= whileStmt );
    public final Statement base_statement() throws RecognitionException {
        Statement value = null;

        Statement s = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:51:5: (s= assignStmt | s= skipStmt | s= readStmt | s= writeStmt | s= ifStmt | s= whileStmt )
            int alt6=6;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt6=1;
                }
                break;
            case 18:
                {
                alt6=2;
                }
                break;
            case 19:
                {
                alt6=3;
                }
                break;
            case 20:
                {
                alt6=4;
                }
                break;
            case 21:
                {
                alt6=5;
                }
                break;
            case 25:
                {
                alt6=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:51:7: s= assignStmt
                    {
                    pushFollow(FOLLOW_assignStmt_in_base_statement305);
                    s=assignStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:52:7: s= skipStmt
                    {
                    pushFollow(FOLLOW_skipStmt_in_base_statement319);
                    s=skipStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 3 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:53:7: s= readStmt
                    {
                    pushFollow(FOLLOW_readStmt_in_base_statement333);
                    s=readStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 4 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:54:7: s= writeStmt
                    {
                    pushFollow(FOLLOW_writeStmt_in_base_statement347);
                    s=writeStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 5 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:55:7: s= ifStmt
                    {
                    pushFollow(FOLLOW_ifStmt_in_base_statement361);
                    s=ifStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 6 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:56:7: s= whileStmt
                    {
                    pushFollow(FOLLOW_whileStmt_in_base_statement375);
                    s=whileStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "base_statement"


    // $ANTLR start "assignStmt"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:59:1: assignStmt returns [Statement value] : IDENTIFIER ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' ) ;
    public final Statement assignStmt() throws RecognitionException {
        Statement value = null;

        Token IDENTIFIER3=null;
        ArithExpr e = null;

        ArithExpr e1 = null;

        ArithExpr e2 = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:60:2: ( IDENTIFIER ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' ) )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:60:4: IDENTIFIER ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' )
            {
            IDENTIFIER3=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assignStmt399); if (state.failed) return value;
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:60:15: ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==17) ) {
                alt7=1;
            }
            else if ( (LA7_0==13) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:60:16: ':=' e= arith_expr ';'
                    {
                    match(input,17,FOLLOW_17_in_assignStmt402); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_assignStmt406);
                    e=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_assignStmt408); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new AssignStatement(IDENTIFIER3.getText(), e); 
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:62:4: '[' e1= arith_expr ']' ':=' e2= arith_expr ';'
                    {
                    match(input,13,FOLLOW_13_in_assignStmt416); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_assignStmt422);
                    e1=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,14,FOLLOW_14_in_assignStmt424); if (state.failed) return value;
                    match(input,17,FOLLOW_17_in_assignStmt425); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_assignStmt429);
                    e2=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_assignStmt431); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ArrayAssignStatement(IDENTIFIER3.getText(), e1, e2); 
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "assignStmt"


    // $ANTLR start "skipStmt"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:65:1: skipStmt returns [Statement value] : 'skip' ';' ;
    public final Statement skipStmt() throws RecognitionException {
        Statement value = null;

        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:66:2: ( 'skip' ';' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:66:4: 'skip' ';'
            {
            match(input,18,FOLLOW_18_in_skipStmt449); if (state.failed) return value;
            match(input,12,FOLLOW_12_in_skipStmt451); if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = new SkipStatement(); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "skipStmt"


    // $ANTLR start "readStmt"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:70:1: readStmt returns [Statement value] : 'read' IDENTIFIER ( ';' | '[' e= arith_expr ']' ';' ) ;
    public final Statement readStmt() throws RecognitionException {
        Statement value = null;

        Token IDENTIFIER4=null;
        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:71:2: ( 'read' IDENTIFIER ( ';' | '[' e= arith_expr ']' ';' ) )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:71:4: 'read' IDENTIFIER ( ';' | '[' e= arith_expr ']' ';' )
            {
            match(input,19,FOLLOW_19_in_readStmt503); if (state.failed) return value;
            IDENTIFIER4=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_readStmt505); if (state.failed) return value;
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:71:21: ( ';' | '[' e= arith_expr ']' ';' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==12) ) {
                alt8=1;
            }
            else if ( (LA8_0==13) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:71:23: ';'
                    {
                    match(input,12,FOLLOW_12_in_readStmt508); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ReadStatement(IDENTIFIER4.getText());
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:73:4: '[' e= arith_expr ']' ';'
                    {
                    match(input,13,FOLLOW_13_in_readStmt516); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_readStmt522);
                    e=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,14,FOLLOW_14_in_readStmt524); if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_readStmt526); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ReadArrayStatement(IDENTIFIER4.getText(),e);
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "readStmt"


    // $ANTLR start "writeStmt"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:78:1: writeStmt returns [Statement value] : 'write' e= arith_expr ';' ;
    public final Statement writeStmt() throws RecognitionException {
        Statement value = null;

        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:79:2: ( 'write' e= arith_expr ';' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:79:4: 'write' e= arith_expr ';'
            {
            match(input,20,FOLLOW_20_in_writeStmt548); if (state.failed) return value;
            pushFollow(FOLLOW_arith_expr_in_writeStmt554);
            e=arith_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,12,FOLLOW_12_in_writeStmt556); if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = new WriteStatement(e);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "writeStmt"


    // $ANTLR start "ifStmt"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:82:1: ifStmt returns [Statement value] : 'if' c= bool_expr 'then' s1= statement 'else' s2= statement 'fi' ;
    public final Statement ifStmt() throws RecognitionException {
        Statement value = null;

        BoolExpr c = null;

        Statement s1 = null;

        Statement s2 = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:83:2: ( 'if' c= bool_expr 'then' s1= statement 'else' s2= statement 'fi' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:83:4: 'if' c= bool_expr 'then' s1= statement 'else' s2= statement 'fi'
            {
            match(input,21,FOLLOW_21_in_ifStmt573); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_ifStmt577);
            c=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,22,FOLLOW_22_in_ifStmt579); if (state.failed) return value;
            pushFollow(FOLLOW_statement_in_ifStmt583);
            s1=statement();

            state._fsp--;
            if (state.failed) return value;
            match(input,23,FOLLOW_23_in_ifStmt588); if (state.failed) return value;
            pushFollow(FOLLOW_statement_in_ifStmt592);
            s2=statement();

            state._fsp--;
            if (state.failed) return value;
            match(input,24,FOLLOW_24_in_ifStmt594); if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = new IfStatement(c,s1,s2); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "ifStmt"


    // $ANTLR start "whileStmt"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:87:1: whileStmt returns [Statement value] : 'while' c= bool_expr 'do' s= statement 'od' ;
    public final Statement whileStmt() throws RecognitionException {
        Statement value = null;

        BoolExpr c = null;

        Statement s = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:88:2: ( 'while' c= bool_expr 'do' s= statement 'od' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:88:4: 'while' c= bool_expr 'do' s= statement 'od'
            {
            match(input,25,FOLLOW_25_in_whileStmt628); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_whileStmt632);
            c=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,26,FOLLOW_26_in_whileStmt634); if (state.failed) return value;
            pushFollow(FOLLOW_statement_in_whileStmt638);
            s=statement();

            state._fsp--;
            if (state.failed) return value;
            match(input,27,FOLLOW_27_in_whileStmt640); if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = new WhileStatement(c,s); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "whileStmt"


    // $ANTLR start "bool_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:93:1: bool_expr returns [BoolExpr value] : e= mid_bool_expr ( '|' e= mid_bool_expr )* ;
    public final BoolExpr bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:94:2: (e= mid_bool_expr ( '|' e= mid_bool_expr )* )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:94:4: e= mid_bool_expr ( '|' e= mid_bool_expr )*
            {
            pushFollow(FOLLOW_mid_bool_expr_in_bool_expr662);
            e=mid_bool_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:95:2: ( '|' e= mid_bool_expr )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==28) ) {
                    int LA9_2 = input.LA(2);

                    if ( (synpred14_TheLang()) ) {
                        alt9=1;
                    }


                }


                switch (alt9) {
            	case 1 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:95:4: '|' e= mid_bool_expr
            	    {
            	    match(input,28,FOLLOW_28_in_bool_expr669); if (state.failed) return value;
            	    pushFollow(FOLLOW_mid_bool_expr_in_bool_expr673);
            	    e=mid_bool_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new OrExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "bool_expr"


    // $ANTLR start "mid_bool_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:99:1: mid_bool_expr returns [BoolExpr value] : e= base_bool_expr ( '&' e= base_bool_expr )* ;
    public final BoolExpr mid_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:100:2: (e= base_bool_expr ( '&' e= base_bool_expr )* )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:100:4: e= base_bool_expr ( '&' e= base_bool_expr )*
            {
            pushFollow(FOLLOW_base_bool_expr_in_mid_bool_expr699);
            e=base_bool_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:101:2: ( '&' e= base_bool_expr )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==29) ) {
                    int LA10_2 = input.LA(2);

                    if ( (synpred15_TheLang()) ) {
                        alt10=1;
                    }


                }


                switch (alt10) {
            	case 1 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:101:3: '&' e= base_bool_expr
            	    {
            	    match(input,29,FOLLOW_29_in_mid_bool_expr705); if (state.failed) return value;
            	    pushFollow(FOLLOW_base_bool_expr_in_mid_bool_expr711);
            	    e=base_bool_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new AndExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "mid_bool_expr"


    // $ANTLR start "base_bool_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:105:1: base_bool_expr returns [BoolExpr value] : (b= not_bool_expr | b= paren_bool_expr | 'true' | 'false' | e1= arith_expr ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr ) );
    public final BoolExpr base_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr b = null;

        ArithExpr e1 = null;

        ArithExpr e2 = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:106:5: (b= not_bool_expr | b= paren_bool_expr | 'true' | 'false' | e1= arith_expr ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr ) )
            int alt12=5;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt12=1;
                }
                break;
            case 39:
                {
                int LA12_2 = input.LA(2);

                if ( (synpred17_TheLang()) ) {
                    alt12=2;
                }
                else if ( (true) ) {
                    alt12=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                alt12=3;
                }
                break;
            case 31:
                {
                alt12=4;
                }
                break;
            case IDENTIFIER:
            case INTEGER:
            case 42:
                {
                alt12=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:106:7: b= not_bool_expr
                    {
                    pushFollow(FOLLOW_not_bool_expr_in_base_bool_expr739);
                    b=not_bool_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = b;
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:107:7: b= paren_bool_expr
                    {
                    pushFollow(FOLLOW_paren_bool_expr_in_base_bool_expr754);
                    b=paren_bool_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = b; 
                    }

                    }
                    break;
                case 3 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:108:7: 'true'
                    {
                    match(input,30,FOLLOW_30_in_base_bool_expr765); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new BoolValueExpr(true); 
                    }

                    }
                    break;
                case 4 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:109:7: 'false'
                    {
                    match(input,31,FOLLOW_31_in_base_bool_expr794); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new BoolValueExpr(false); 
                    }

                    }
                    break;
                case 5 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:110:7: e1= arith_expr ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr )
                    {
                    pushFollow(FOLLOW_arith_expr_in_base_bool_expr824);
                    e1=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:110:21: ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr )
                    int alt11=6;
                    switch ( input.LA(1) ) {
                    case 32:
                        {
                        alt11=1;
                        }
                        break;
                    case 33:
                        {
                        alt11=2;
                        }
                        break;
                    case 34:
                        {
                        alt11=3;
                        }
                        break;
                    case 35:
                        {
                        alt11=4;
                        }
                        break;
                    case 36:
                        {
                        alt11=5;
                        }
                        break;
                    case 37:
                        {
                        alt11=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:110:23: '=' e2= arith_expr
                            {
                            match(input,32,FOLLOW_32_in_base_bool_expr828); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr832);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new EqualsExpr(e1,e2); 
                            }

                            }
                            break;
                        case 2 :
                            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:111:23: '<=' e2= arith_expr
                            {
                            match(input,33,FOLLOW_33_in_base_bool_expr859); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr863);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new LessThanEqualsExpr(e1,e2); 
                            }

                            }
                            break;
                        case 3 :
                            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:112:23: '<' e2= arith_expr
                            {
                            match(input,34,FOLLOW_34_in_base_bool_expr890); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr894);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new LessThanExpr(e1,e2); 
                            }

                            }
                            break;
                        case 4 :
                            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:113:23: '>=' e2= arith_expr
                            {
                            match(input,35,FOLLOW_35_in_base_bool_expr922); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr926);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new GreaterThanEqualsExpr(e1,e2); 
                            }

                            }
                            break;
                        case 5 :
                            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:114:23: '>' e2= arith_expr
                            {
                            match(input,36,FOLLOW_36_in_base_bool_expr953); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr957);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new GreaterThanExpr(e1,e2); 
                            }

                            }
                            break;
                        case 6 :
                            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:115:23: '!=' e2= arith_expr
                            {
                            match(input,37,FOLLOW_37_in_base_bool_expr985); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr989);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new NotEqualsExpr(e1,e2); 
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "base_bool_expr"


    // $ANTLR start "not_bool_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:118:1: not_bool_expr returns [BoolExpr value] : '!' e= bool_expr ;
    public final BoolExpr not_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:119:2: ( '!' e= bool_expr )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:119:4: '!' e= bool_expr
            {
            match(input,38,FOLLOW_38_in_not_bool_expr1017); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_not_bool_expr1023);
            e=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = new NotExpr(e); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "not_bool_expr"


    // $ANTLR start "paren_bool_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:123:1: paren_bool_expr returns [BoolExpr value] : '(' e= bool_expr ')' ;
    public final BoolExpr paren_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:124:5: ( '(' e= bool_expr ')' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:124:7: '(' e= bool_expr ')'
            {
            match(input,39,FOLLOW_39_in_paren_bool_expr1046); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_paren_bool_expr1050);
            e=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,40,FOLLOW_40_in_paren_bool_expr1052); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = e;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "paren_bool_expr"


    // $ANTLR start "arith_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:128:1: arith_expr returns [ArithExpr value] : e= mult_div_arith_expr ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )* ;
    public final ArithExpr arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:129:5: (e= mult_div_arith_expr ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )* )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:129:7: e= mult_div_arith_expr ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )*
            {
            pushFollow(FOLLOW_mult_div_arith_expr_in_arith_expr1078);
            e=mult_div_arith_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:130:7: ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )*
            loop13:
            do {
                int alt13=3;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==41) ) {
                    alt13=1;
                }
                else if ( (LA13_0==42) ) {
                    alt13=2;
                }


                switch (alt13) {
            	case 1 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:130:9: '+' e= mult_div_arith_expr
            	    {
            	    match(input,41,FOLLOW_41_in_arith_expr1096); if (state.failed) return value;
            	    pushFollow(FOLLOW_mult_div_arith_expr_in_arith_expr1100);
            	    e=mult_div_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new PlusExpr(value,e); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:131:9: '-' e= mult_div_arith_expr
            	    {
            	    match(input,42,FOLLOW_42_in_arith_expr1113); if (state.failed) return value;
            	    pushFollow(FOLLOW_mult_div_arith_expr_in_arith_expr1117);
            	    e=mult_div_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new MinusExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "arith_expr"


    // $ANTLR start "mult_div_arith_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:134:1: mult_div_arith_expr returns [ArithExpr value] : e= un_min_arith_expr ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )* ;
    public final ArithExpr mult_div_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:135:5: (e= un_min_arith_expr ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )* )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:135:7: e= un_min_arith_expr ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )*
            {
            pushFollow(FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1145);
            e=un_min_arith_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:136:7: ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )*
            loop14:
            do {
                int alt14=3;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==43) ) {
                    alt14=1;
                }
                else if ( (LA14_0==44) ) {
                    alt14=2;
                }


                switch (alt14) {
            	case 1 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:136:9: '*' e= un_min_arith_expr
            	    {
            	    match(input,43,FOLLOW_43_in_mult_div_arith_expr1163); if (state.failed) return value;
            	    pushFollow(FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1167);
            	    e=un_min_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new MultExpr(value,e); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:137:9: '/' e= un_min_arith_expr
            	    {
            	    match(input,44,FOLLOW_44_in_mult_div_arith_expr1180); if (state.failed) return value;
            	    pushFollow(FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1184);
            	    e=un_min_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new DivExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "mult_div_arith_expr"


    // $ANTLR start "un_min_arith_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:140:1: un_min_arith_expr returns [ ArithExpr value] : (e= base_arith_expr | '-' e= base_arith_expr );
    public final ArithExpr un_min_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:141:5: (e= base_arith_expr | '-' e= base_arith_expr )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=IDENTIFIER && LA15_0<=INTEGER)||LA15_0==39) ) {
                alt15=1;
            }
            else if ( (LA15_0==42) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:141:7: e= base_arith_expr
                    {
                    pushFollow(FOLLOW_base_arith_expr_in_un_min_arith_expr1216);
                    e=base_arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e; 
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:142:6: '-' e= base_arith_expr
                    {
                    match(input,42,FOLLOW_42_in_un_min_arith_expr1228); if (state.failed) return value;
                    pushFollow(FOLLOW_base_arith_expr_in_un_min_arith_expr1232);
                    e=base_arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new UnMinExpr(e); 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "un_min_arith_expr"


    // $ANTLR start "base_arith_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:145:1: base_arith_expr returns [ArithExpr value] : ( INTEGER | IDENTIFIER | e= array_arith_expr | e= paren_arith_expr );
    public final ArithExpr base_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        Token INTEGER5=null;
        Token IDENTIFIER6=null;
        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:146:5: ( INTEGER | IDENTIFIER | e= array_arith_expr | e= paren_arith_expr )
            int alt16=4;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt16=1;
                }
                break;
            case IDENTIFIER:
                {
                int LA16_2 = input.LA(2);

                if ( (LA16_2==13) ) {
                    alt16=3;
                }
                else if ( (LA16_2==EOF||LA16_2==12||LA16_2==14||LA16_2==22||LA16_2==26||(LA16_2>=28 && LA16_2<=29)||(LA16_2>=32 && LA16_2<=37)||(LA16_2>=40 && LA16_2<=44)) ) {
                    alt16=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 2, input);

                    throw nvae;
                }
                }
                break;
            case 39:
                {
                alt16=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:146:7: INTEGER
                    {
                    INTEGER5=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_base_arith_expr1256); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new NumExpr(Integer.parseInt(INTEGER5.getText())); 
                    }

                    }
                    break;
                case 2 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:147:7: IDENTIFIER
                    {
                    IDENTIFIER6=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_base_arith_expr1268); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new IdExpr(IDENTIFIER6.getText()); 
                    }

                    }
                    break;
                case 3 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:148:7: e= array_arith_expr
                    {
                    pushFollow(FOLLOW_array_arith_expr_in_base_arith_expr1282);
                    e=array_arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e; 
                    }

                    }
                    break;
                case 4 :
                    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:149:7: e= paren_arith_expr
                    {
                    pushFollow(FOLLOW_paren_arith_expr_in_base_arith_expr1294);
                    e=paren_arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e; 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "base_arith_expr"


    // $ANTLR start "array_arith_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:152:1: array_arith_expr returns [ArithExpr value] : IDENTIFIER '[' e= arith_expr ']' ;
    public final ArithExpr array_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        Token IDENTIFIER7=null;
        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:153:5: ( IDENTIFIER '[' e= arith_expr ']' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:153:7: IDENTIFIER '[' e= arith_expr ']'
            {
            IDENTIFIER7=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_array_arith_expr1321); if (state.failed) return value;
            match(input,13,FOLLOW_13_in_array_arith_expr1323); if (state.failed) return value;
            pushFollow(FOLLOW_arith_expr_in_array_arith_expr1327);
            e=arith_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,14,FOLLOW_14_in_array_arith_expr1329); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ArrayExpr(IDENTIFIER7.getText(), e);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "array_arith_expr"


    // $ANTLR start "paren_arith_expr"
    // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:156:1: paren_arith_expr returns [ArithExpr value] : '(' e= arith_expr ')' ;
    public final ArithExpr paren_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:157:5: ( '(' e= arith_expr ')' )
            // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:157:7: '(' e= arith_expr ')'
            {
            match(input,39,FOLLOW_39_in_paren_arith_expr1357); if (state.failed) return value;
            pushFollow(FOLLOW_arith_expr_in_paren_arith_expr1361);
            e=arith_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,40,FOLLOW_40_in_paren_arith_expr1363); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = e;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "paren_arith_expr"

    // $ANTLR start synpred14_TheLang
    public final void synpred14_TheLang_fragment() throws RecognitionException {   
        BoolExpr e = null;


        // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:95:4: ( '|' e= mid_bool_expr )
        // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:95:4: '|' e= mid_bool_expr
        {
        match(input,28,FOLLOW_28_in_synpred14_TheLang669); if (state.failed) return ;
        pushFollow(FOLLOW_mid_bool_expr_in_synpred14_TheLang673);
        e=mid_bool_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_TheLang

    // $ANTLR start synpred15_TheLang
    public final void synpred15_TheLang_fragment() throws RecognitionException {   
        BoolExpr e = null;


        // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:101:3: ( '&' e= base_bool_expr )
        // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:101:3: '&' e= base_bool_expr
        {
        match(input,29,FOLLOW_29_in_synpred15_TheLang705); if (state.failed) return ;
        pushFollow(FOLLOW_base_bool_expr_in_synpred15_TheLang711);
        e=base_bool_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_TheLang

    // $ANTLR start synpred17_TheLang
    public final void synpred17_TheLang_fragment() throws RecognitionException {   
        BoolExpr b = null;


        // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:107:7: (b= paren_bool_expr )
        // E:\\Dropbox\\Courses\\program analysis\\code\\program_analysis\\src\\parser\\TheLang.g:107:7: b= paren_bool_expr
        {
        pushFollow(FOLLOW_paren_bool_expr_in_synpred17_TheLang754);
        b=paren_bool_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred17_TheLang

    // Delegated rules

    public final boolean synpred14_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_9_in_program54 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_declaration_in_program60 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_program64 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program66 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_program82 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_program84 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_base_declaration_in_declaration118 = new BitSet(new long[]{0x0000000000018802L});
    public static final BitSet FOLLOW_base_declaration_in_declaration135 = new BitSet(new long[]{0x0000000000018802L});
    public static final BitSet FOLLOW_level_in_base_declaration164 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_base_declaration174 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_base_declaration176 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_base_declaration179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_base_declaration196 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INTEGER_in_base_declaration198 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_base_declaration200 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_base_declaration202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_level0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_base_statement_in_statement255 = new BitSet(new long[]{0x00000000023D8812L});
    public static final BitSet FOLLOW_base_statement_in_statement275 = new BitSet(new long[]{0x00000000023D8812L});
    public static final BitSet FOLLOW_assignStmt_in_base_statement305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_skipStmt_in_base_statement319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_readStmt_in_base_statement333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_writeStmt_in_base_statement347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStmt_in_base_statement361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_base_statement375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_assignStmt399 = new BitSet(new long[]{0x0000000000022000L});
    public static final BitSet FOLLOW_17_in_assignStmt402 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_assignStmt406 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_assignStmt408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_assignStmt416 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_assignStmt422 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_assignStmt424 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_assignStmt425 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_assignStmt429 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_assignStmt431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_skipStmt449 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_skipStmt451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_readStmt503 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_readStmt505 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_readStmt508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_readStmt516 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_readStmt522 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_readStmt524 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_readStmt526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_writeStmt548 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_writeStmt554 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_writeStmt556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ifStmt573 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_ifStmt577 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ifStmt579 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_ifStmt583 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ifStmt588 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_ifStmt592 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ifStmt594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_whileStmt628 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_whileStmt632 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_whileStmt634 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_whileStmt638 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_whileStmt640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mid_bool_expr_in_bool_expr662 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_bool_expr669 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_mid_bool_expr_in_bool_expr673 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_base_bool_expr_in_mid_bool_expr699 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_mid_bool_expr705 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_base_bool_expr_in_mid_bool_expr711 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_not_bool_expr_in_base_bool_expr739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_bool_expr_in_base_bool_expr754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_base_bool_expr765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_base_bool_expr794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr824 = new BitSet(new long[]{0x0000003F00000000L});
    public static final BitSet FOLLOW_32_in_base_bool_expr828 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_base_bool_expr859 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_base_bool_expr890 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_base_bool_expr922 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_base_bool_expr953 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_base_bool_expr985 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_not_bool_expr1017 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_not_bool_expr1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_paren_bool_expr1046 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_paren_bool_expr1050 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_paren_bool_expr1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mult_div_arith_expr_in_arith_expr1078 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_41_in_arith_expr1096 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_mult_div_arith_expr_in_arith_expr1100 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_42_in_arith_expr1113 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_mult_div_arith_expr_in_arith_expr1117 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1145 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_43_in_mult_div_arith_expr1163 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1167 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_44_in_mult_div_arith_expr1180 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1184 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_base_arith_expr_in_un_min_arith_expr1216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_un_min_arith_expr1228 = new BitSet(new long[]{0x0000008000000030L});
    public static final BitSet FOLLOW_base_arith_expr_in_un_min_arith_expr1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_base_arith_expr1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_base_arith_expr1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_array_arith_expr_in_base_arith_expr1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_arith_expr_in_base_arith_expr1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_array_arith_expr1321 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_array_arith_expr1323 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_array_arith_expr1327 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_array_arith_expr1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_paren_arith_expr1357 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_paren_arith_expr1361 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_paren_arith_expr1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_synpred14_TheLang669 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_mid_bool_expr_in_synpred14_TheLang673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_synpred15_TheLang705 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_base_bool_expr_in_synpred15_TheLang711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_bool_expr_in_synpred17_TheLang754 = new BitSet(new long[]{0x0000000000000002L});

}