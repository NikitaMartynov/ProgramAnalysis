// $ANTLR 3.3 Nov 30, 2010 12:45:30 E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g 2013-12-01 13:26:14

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
    public String getGrammarFileName() { return "E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g"; }



    // $ANTLR start "program"
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:23:1: program returns [Program value] : 'program' (d= declaration s= statement 'end' EOF | s= statement 'end' EOF ) ;
    public final Program program() throws RecognitionException {
        Program value = null;

        Declaration d = null;

        Statement s = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:24:5: ( 'program' (d= declaration s= statement 'end' EOF | s= statement 'end' EOF ) )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:24:7: 'program' (d= declaration s= statement 'end' EOF | s= statement 'end' EOF )
            {
            match(input,9,FOLLOW_9_in_program54); if (state.failed) return value;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:24:17: (d= declaration s= statement 'end' EOF | s= statement 'end' EOF )
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
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:24:19: d= declaration s= statement 'end' EOF
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
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:25:9: s= statement 'end' EOF
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:29:1: declaration returns [Declaration value] : d= base_declaration (d= base_declaration )* ;
    public final Declaration declaration() throws RecognitionException {
        Declaration value = null;

        Declaration d = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:30:5: (d= base_declaration (d= base_declaration )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:30:7: d= base_declaration (d= base_declaration )*
            {
            pushFollow(FOLLOW_base_declaration_in_declaration118);
            d=base_declaration();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = d; 
            }
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:31:7: (d= base_declaration )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11||(LA2_0>=15 && LA2_0<=16)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:31:9: d= base_declaration
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:34:1: base_declaration returns [Declaration value] : ( (l= level 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) ) | 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) );
    public final Declaration base_declaration() throws RecognitionException {
        Declaration value = null;

        Token IDENTIFIER1=null;
        Token INTEGER2=null;
        Token IDENTIFIER3=null;
        Token INTEGER4=null;
        SecurityLevel l = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:35:5: ( (l= level 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) ) | 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=15 && LA5_0<=16)) ) {
                alt5=1;
            }
            else if ( (LA5_0==11) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:35:7: (l= level 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) )
                    {
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:35:7: (l= level 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' ) )
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:35:8: l= level 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' )
                    {
                    pushFollow(FOLLOW_level_in_base_declaration169);
                    l=level();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,11,FOLLOW_11_in_base_declaration177); if (state.failed) return value;
                    IDENTIFIER1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_base_declaration179); if (state.failed) return value;
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:36:24: ( ';' | '[' INTEGER ']' ';' )
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==12) ) {
                        alt3=1;
                    }
                    else if ( (LA3_0==13) ) {
                        alt3=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                        throw nvae;
                    }
                    switch (alt3) {
                        case 1 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:36:25: ';'
                            {
                            match(input,12,FOLLOW_12_in_base_declaration182); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new VariableDeclaration(l,IDENTIFIER1.getText()); 
                            }

                            }
                            break;
                        case 2 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:38:8: '[' INTEGER ']' ';'
                            {
                            match(input,13,FOLLOW_13_in_base_declaration199); if (state.failed) return value;
                            INTEGER2=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_base_declaration201); if (state.failed) return value;
                            match(input,14,FOLLOW_14_in_base_declaration203); if (state.failed) return value;
                            match(input,12,FOLLOW_12_in_base_declaration205); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new ArrayDeclaration(l,IDENTIFIER1.getText(), Integer.parseInt(INTEGER2.getText()));
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:42:7: 'int' IDENTIFIER ( ';' | '[' INTEGER ']' ';' )
                    {
                    match(input,11,FOLLOW_11_in_base_declaration239); if (state.failed) return value;
                    IDENTIFIER3=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_base_declaration241); if (state.failed) return value;
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:42:24: ( ';' | '[' INTEGER ']' ';' )
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
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:42:25: ';'
                            {
                            match(input,12,FOLLOW_12_in_base_declaration244); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new VariableDeclaration(IDENTIFIER3.getText()); 
                            }

                            }
                            break;
                        case 2 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:44:8: '[' INTEGER ']' ';'
                            {
                            match(input,13,FOLLOW_13_in_base_declaration261); if (state.failed) return value;
                            INTEGER4=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_base_declaration263); if (state.failed) return value;
                            match(input,14,FOLLOW_14_in_base_declaration265); if (state.failed) return value;
                            match(input,12,FOLLOW_12_in_base_declaration267); if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new ArrayDeclaration(IDENTIFIER3.getText(), Integer.parseInt(INTEGER4.getText()));
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
    // $ANTLR end "base_declaration"


    // $ANTLR start "level"
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:49:1: level returns [SecurityLevel value] : ( 'low' | 'high' );
    public final SecurityLevel level() throws RecognitionException {
        SecurityLevel value = null;

        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:50:2: ( 'low' | 'high' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            else if ( (LA6_0==16) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:50:4: 'low'
                    {
                    match(input,15,FOLLOW_15_in_level302); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new SecurityLevelLow();
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:51:4: 'high'
                    {
                    match(input,16,FOLLOW_16_in_level309); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                      value = new SecurityLevelHigh();
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
    // $ANTLR end "level"


    // $ANTLR start "statement"
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:53:1: statement returns [Statement value] : s= base_statement (s= base_statement )* ;
    public final Statement statement() throws RecognitionException {
        Statement value = null;

        Statement s = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:54:5: (s= base_statement (s= base_statement )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:54:7: s= base_statement (s= base_statement )*
            {
            pushFollow(FOLLOW_base_statement_in_statement329);
            s=base_statement();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = s; 
            }
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:55:7: (s= base_statement )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==IDENTIFIER||(LA7_0>=18 && LA7_0<=21)||LA7_0==25) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:55:9: s= base_statement
            	    {
            	    pushFollow(FOLLOW_base_statement_in_statement349);
            	    s=base_statement();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new SeqStatement(value,s); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop7;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:58:1: base_statement returns [Statement value] : (s= assignStmt | s= skipStmt | s= readStmt | s= writeStmt | s= ifStmt | s= whileStmt );
    public final Statement base_statement() throws RecognitionException {
        Statement value = null;

        Statement s = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:59:5: (s= assignStmt | s= skipStmt | s= readStmt | s= writeStmt | s= ifStmt | s= whileStmt )
            int alt8=6;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt8=1;
                }
                break;
            case 18:
                {
                alt8=2;
                }
                break;
            case 19:
                {
                alt8=3;
                }
                break;
            case 20:
                {
                alt8=4;
                }
                break;
            case 21:
                {
                alt8=5;
                }
                break;
            case 25:
                {
                alt8=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:59:7: s= assignStmt
                    {
                    pushFollow(FOLLOW_assignStmt_in_base_statement379);
                    s=assignStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:60:7: s= skipStmt
                    {
                    pushFollow(FOLLOW_skipStmt_in_base_statement393);
                    s=skipStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 3 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:61:7: s= readStmt
                    {
                    pushFollow(FOLLOW_readStmt_in_base_statement407);
                    s=readStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 4 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:62:7: s= writeStmt
                    {
                    pushFollow(FOLLOW_writeStmt_in_base_statement421);
                    s=writeStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 5 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:63:7: s= ifStmt
                    {
                    pushFollow(FOLLOW_ifStmt_in_base_statement435);
                    s=ifStmt();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = s; 
                    }

                    }
                    break;
                case 6 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:64:7: s= whileStmt
                    {
                    pushFollow(FOLLOW_whileStmt_in_base_statement449);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:67:1: assignStmt returns [Statement value] : IDENTIFIER ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' ) ;
    public final Statement assignStmt() throws RecognitionException {
        Statement value = null;

        Token IDENTIFIER5=null;
        ArithExpr e = null;

        ArithExpr e1 = null;

        ArithExpr e2 = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:68:2: ( IDENTIFIER ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' ) )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:68:4: IDENTIFIER ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' )
            {
            IDENTIFIER5=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assignStmt473); if (state.failed) return value;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:68:15: ( ':=' e= arith_expr ';' | '[' e1= arith_expr ']' ':=' e2= arith_expr ';' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            else if ( (LA9_0==13) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:68:16: ':=' e= arith_expr ';'
                    {
                    match(input,17,FOLLOW_17_in_assignStmt476); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_assignStmt480);
                    e=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_assignStmt482); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new AssignStatement(IDENTIFIER5.getText(), e); 
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:70:4: '[' e1= arith_expr ']' ':=' e2= arith_expr ';'
                    {
                    match(input,13,FOLLOW_13_in_assignStmt490); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_assignStmt496);
                    e1=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,14,FOLLOW_14_in_assignStmt498); if (state.failed) return value;
                    match(input,17,FOLLOW_17_in_assignStmt499); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_assignStmt503);
                    e2=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_assignStmt505); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ArrayAssignStatement(IDENTIFIER5.getText(), e1, e2); 
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:73:1: skipStmt returns [Statement value] : 'skip' ';' ;
    public final Statement skipStmt() throws RecognitionException {
        Statement value = null;

        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:74:2: ( 'skip' ';' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:74:4: 'skip' ';'
            {
            match(input,18,FOLLOW_18_in_skipStmt523); if (state.failed) return value;
            match(input,12,FOLLOW_12_in_skipStmt525); if (state.failed) return value;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:78:1: readStmt returns [Statement value] : 'read' IDENTIFIER ( ';' | '[' e= arith_expr ']' ';' ) ;
    public final Statement readStmt() throws RecognitionException {
        Statement value = null;

        Token IDENTIFIER6=null;
        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:79:2: ( 'read' IDENTIFIER ( ';' | '[' e= arith_expr ']' ';' ) )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:79:4: 'read' IDENTIFIER ( ';' | '[' e= arith_expr ']' ';' )
            {
            match(input,19,FOLLOW_19_in_readStmt577); if (state.failed) return value;
            IDENTIFIER6=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_readStmt579); if (state.failed) return value;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:79:21: ( ';' | '[' e= arith_expr ']' ';' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==12) ) {
                alt10=1;
            }
            else if ( (LA10_0==13) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:79:23: ';'
                    {
                    match(input,12,FOLLOW_12_in_readStmt582); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ReadStatement(IDENTIFIER6.getText());
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:81:4: '[' e= arith_expr ']' ';'
                    {
                    match(input,13,FOLLOW_13_in_readStmt590); if (state.failed) return value;
                    pushFollow(FOLLOW_arith_expr_in_readStmt596);
                    e=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    match(input,14,FOLLOW_14_in_readStmt598); if (state.failed) return value;
                    match(input,12,FOLLOW_12_in_readStmt600); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new ReadArrayStatement(IDENTIFIER6.getText(),e);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:86:1: writeStmt returns [Statement value] : 'write' e= arith_expr ';' ;
    public final Statement writeStmt() throws RecognitionException {
        Statement value = null;

        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:87:2: ( 'write' e= arith_expr ';' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:87:4: 'write' e= arith_expr ';'
            {
            match(input,20,FOLLOW_20_in_writeStmt622); if (state.failed) return value;
            pushFollow(FOLLOW_arith_expr_in_writeStmt628);
            e=arith_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,12,FOLLOW_12_in_writeStmt630); if (state.failed) return value;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:90:1: ifStmt returns [Statement value] : 'if' c= bool_expr 'then' s1= statement 'else' s2= statement 'fi' ;
    public final Statement ifStmt() throws RecognitionException {
        Statement value = null;

        BoolExpr c = null;

        Statement s1 = null;

        Statement s2 = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:91:2: ( 'if' c= bool_expr 'then' s1= statement 'else' s2= statement 'fi' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:91:4: 'if' c= bool_expr 'then' s1= statement 'else' s2= statement 'fi'
            {
            match(input,21,FOLLOW_21_in_ifStmt647); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_ifStmt651);
            c=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,22,FOLLOW_22_in_ifStmt653); if (state.failed) return value;
            pushFollow(FOLLOW_statement_in_ifStmt657);
            s1=statement();

            state._fsp--;
            if (state.failed) return value;
            match(input,23,FOLLOW_23_in_ifStmt662); if (state.failed) return value;
            pushFollow(FOLLOW_statement_in_ifStmt666);
            s2=statement();

            state._fsp--;
            if (state.failed) return value;
            match(input,24,FOLLOW_24_in_ifStmt668); if (state.failed) return value;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:95:1: whileStmt returns [Statement value] : 'while' c= bool_expr 'do' s= statement 'od' ;
    public final Statement whileStmt() throws RecognitionException {
        Statement value = null;

        BoolExpr c = null;

        Statement s = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:96:2: ( 'while' c= bool_expr 'do' s= statement 'od' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:96:4: 'while' c= bool_expr 'do' s= statement 'od'
            {
            match(input,25,FOLLOW_25_in_whileStmt702); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_whileStmt706);
            c=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,26,FOLLOW_26_in_whileStmt708); if (state.failed) return value;
            pushFollow(FOLLOW_statement_in_whileStmt712);
            s=statement();

            state._fsp--;
            if (state.failed) return value;
            match(input,27,FOLLOW_27_in_whileStmt714); if (state.failed) return value;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:101:1: bool_expr returns [BoolExpr value] : e= mid_bool_expr ( '|' e= mid_bool_expr )* ;
    public final BoolExpr bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:102:2: (e= mid_bool_expr ( '|' e= mid_bool_expr )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:102:4: e= mid_bool_expr ( '|' e= mid_bool_expr )*
            {
            pushFollow(FOLLOW_mid_bool_expr_in_bool_expr736);
            e=mid_bool_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:103:2: ( '|' e= mid_bool_expr )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==28) ) {
                    int LA11_2 = input.LA(2);

                    if ( (synpred15_TheLang()) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:103:4: '|' e= mid_bool_expr
            	    {
            	    match(input,28,FOLLOW_28_in_bool_expr743); if (state.failed) return value;
            	    pushFollow(FOLLOW_mid_bool_expr_in_bool_expr747);
            	    e=mid_bool_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new OrExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop11;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:107:1: mid_bool_expr returns [BoolExpr value] : e= base_bool_expr ( '&' e= base_bool_expr )* ;
    public final BoolExpr mid_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:108:2: (e= base_bool_expr ( '&' e= base_bool_expr )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:108:4: e= base_bool_expr ( '&' e= base_bool_expr )*
            {
            pushFollow(FOLLOW_base_bool_expr_in_mid_bool_expr773);
            e=base_bool_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:109:2: ( '&' e= base_bool_expr )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==29) ) {
                    int LA12_2 = input.LA(2);

                    if ( (synpred16_TheLang()) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:109:3: '&' e= base_bool_expr
            	    {
            	    match(input,29,FOLLOW_29_in_mid_bool_expr779); if (state.failed) return value;
            	    pushFollow(FOLLOW_base_bool_expr_in_mid_bool_expr785);
            	    e=base_bool_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new AndExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop12;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:113:1: base_bool_expr returns [BoolExpr value] : (b= not_bool_expr | b= paren_bool_expr | 'true' | 'false' | e1= arith_expr ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr ) );
    public final BoolExpr base_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr b = null;

        ArithExpr e1 = null;

        ArithExpr e2 = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:114:5: (b= not_bool_expr | b= paren_bool_expr | 'true' | 'false' | e1= arith_expr ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr ) )
            int alt14=5;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt14=1;
                }
                break;
            case 39:
                {
                int LA14_2 = input.LA(2);

                if ( (synpred18_TheLang()) ) {
                    alt14=2;
                }
                else if ( (true) ) {
                    alt14=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 2, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                alt14=3;
                }
                break;
            case 31:
                {
                alt14=4;
                }
                break;
            case IDENTIFIER:
            case INTEGER:
            case 42:
                {
                alt14=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:114:7: b= not_bool_expr
                    {
                    pushFollow(FOLLOW_not_bool_expr_in_base_bool_expr813);
                    b=not_bool_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = b;
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:115:7: b= paren_bool_expr
                    {
                    pushFollow(FOLLOW_paren_bool_expr_in_base_bool_expr828);
                    b=paren_bool_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = b; 
                    }

                    }
                    break;
                case 3 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:116:7: 'true'
                    {
                    match(input,30,FOLLOW_30_in_base_bool_expr839); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new BoolValueExpr(true); 
                    }

                    }
                    break;
                case 4 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:117:7: 'false'
                    {
                    match(input,31,FOLLOW_31_in_base_bool_expr868); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new BoolValueExpr(false); 
                    }

                    }
                    break;
                case 5 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:118:7: e1= arith_expr ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr )
                    {
                    pushFollow(FOLLOW_arith_expr_in_base_bool_expr898);
                    e1=arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:118:21: ( '=' e2= arith_expr | '<=' e2= arith_expr | '<' e2= arith_expr | '>=' e2= arith_expr | '>' e2= arith_expr | '!=' e2= arith_expr )
                    int alt13=6;
                    switch ( input.LA(1) ) {
                    case 32:
                        {
                        alt13=1;
                        }
                        break;
                    case 33:
                        {
                        alt13=2;
                        }
                        break;
                    case 34:
                        {
                        alt13=3;
                        }
                        break;
                    case 35:
                        {
                        alt13=4;
                        }
                        break;
                    case 36:
                        {
                        alt13=5;
                        }
                        break;
                    case 37:
                        {
                        alt13=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return value;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 0, input);

                        throw nvae;
                    }

                    switch (alt13) {
                        case 1 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:118:23: '=' e2= arith_expr
                            {
                            match(input,32,FOLLOW_32_in_base_bool_expr902); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr906);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new EqualsExpr(e1,e2); 
                            }

                            }
                            break;
                        case 2 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:119:23: '<=' e2= arith_expr
                            {
                            match(input,33,FOLLOW_33_in_base_bool_expr933); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr937);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new LessThanEqualsExpr(e1,e2); 
                            }

                            }
                            break;
                        case 3 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:120:23: '<' e2= arith_expr
                            {
                            match(input,34,FOLLOW_34_in_base_bool_expr964); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr968);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new LessThanExpr(e1,e2); 
                            }

                            }
                            break;
                        case 4 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:121:23: '>=' e2= arith_expr
                            {
                            match(input,35,FOLLOW_35_in_base_bool_expr996); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr1000);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new GreaterThanEqualsExpr(e1,e2); 
                            }

                            }
                            break;
                        case 5 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:122:23: '>' e2= arith_expr
                            {
                            match(input,36,FOLLOW_36_in_base_bool_expr1027); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr1031);
                            e2=arith_expr();

                            state._fsp--;
                            if (state.failed) return value;
                            if ( state.backtracking==0 ) {
                               value = new GreaterThanExpr(e1,e2); 
                            }

                            }
                            break;
                        case 6 :
                            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:123:23: '!=' e2= arith_expr
                            {
                            match(input,37,FOLLOW_37_in_base_bool_expr1059); if (state.failed) return value;
                            pushFollow(FOLLOW_arith_expr_in_base_bool_expr1063);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:126:1: not_bool_expr returns [BoolExpr value] : '!' e= bool_expr ;
    public final BoolExpr not_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:127:2: ( '!' e= bool_expr )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:127:4: '!' e= bool_expr
            {
            match(input,38,FOLLOW_38_in_not_bool_expr1091); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_not_bool_expr1097);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:131:1: paren_bool_expr returns [BoolExpr value] : '(' e= bool_expr ')' ;
    public final BoolExpr paren_bool_expr() throws RecognitionException {
        BoolExpr value = null;

        BoolExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:132:5: ( '(' e= bool_expr ')' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:132:7: '(' e= bool_expr ')'
            {
            match(input,39,FOLLOW_39_in_paren_bool_expr1120); if (state.failed) return value;
            pushFollow(FOLLOW_bool_expr_in_paren_bool_expr1124);
            e=bool_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,40,FOLLOW_40_in_paren_bool_expr1126); if (state.failed) return value;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:136:1: arith_expr returns [ArithExpr value] : e= mult_div_arith_expr ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )* ;
    public final ArithExpr arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:137:5: (e= mult_div_arith_expr ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:137:7: e= mult_div_arith_expr ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )*
            {
            pushFollow(FOLLOW_mult_div_arith_expr_in_arith_expr1152);
            e=mult_div_arith_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:138:7: ( '+' e= mult_div_arith_expr | '-' e= mult_div_arith_expr )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==41) ) {
                    alt15=1;
                }
                else if ( (LA15_0==42) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:138:9: '+' e= mult_div_arith_expr
            	    {
            	    match(input,41,FOLLOW_41_in_arith_expr1170); if (state.failed) return value;
            	    pushFollow(FOLLOW_mult_div_arith_expr_in_arith_expr1174);
            	    e=mult_div_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new PlusExpr(value,e); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:139:9: '-' e= mult_div_arith_expr
            	    {
            	    match(input,42,FOLLOW_42_in_arith_expr1187); if (state.failed) return value;
            	    pushFollow(FOLLOW_mult_div_arith_expr_in_arith_expr1191);
            	    e=mult_div_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new MinusExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop15;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:142:1: mult_div_arith_expr returns [ArithExpr value] : e= un_min_arith_expr ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )* ;
    public final ArithExpr mult_div_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:143:5: (e= un_min_arith_expr ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:143:7: e= un_min_arith_expr ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )*
            {
            pushFollow(FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1219);
            e=un_min_arith_expr();

            state._fsp--;
            if (state.failed) return value;
            if ( state.backtracking==0 ) {
               value = e; 
            }
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:144:7: ( '*' e= un_min_arith_expr | '/' e= un_min_arith_expr )*
            loop16:
            do {
                int alt16=3;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==43) ) {
                    alt16=1;
                }
                else if ( (LA16_0==44) ) {
                    alt16=2;
                }


                switch (alt16) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:144:9: '*' e= un_min_arith_expr
            	    {
            	    match(input,43,FOLLOW_43_in_mult_div_arith_expr1237); if (state.failed) return value;
            	    pushFollow(FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1241);
            	    e=un_min_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new MultExpr(value,e); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:145:9: '/' e= un_min_arith_expr
            	    {
            	    match(input,44,FOLLOW_44_in_mult_div_arith_expr1254); if (state.failed) return value;
            	    pushFollow(FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1258);
            	    e=un_min_arith_expr();

            	    state._fsp--;
            	    if (state.failed) return value;
            	    if ( state.backtracking==0 ) {
            	       value = new DivExpr(value,e); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:148:1: un_min_arith_expr returns [ ArithExpr value] : (e= base_arith_expr | '-' e= base_arith_expr );
    public final ArithExpr un_min_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:149:5: (e= base_arith_expr | '-' e= base_arith_expr )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=IDENTIFIER && LA17_0<=INTEGER)||LA17_0==39) ) {
                alt17=1;
            }
            else if ( (LA17_0==42) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:149:7: e= base_arith_expr
                    {
                    pushFollow(FOLLOW_base_arith_expr_in_un_min_arith_expr1290);
                    e=base_arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e; 
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:150:6: '-' e= base_arith_expr
                    {
                    match(input,42,FOLLOW_42_in_un_min_arith_expr1302); if (state.failed) return value;
                    pushFollow(FOLLOW_base_arith_expr_in_un_min_arith_expr1306);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:153:1: base_arith_expr returns [ArithExpr value] : ( INTEGER | IDENTIFIER | e= array_arith_expr | e= paren_arith_expr );
    public final ArithExpr base_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        Token INTEGER7=null;
        Token IDENTIFIER8=null;
        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:154:5: ( INTEGER | IDENTIFIER | e= array_arith_expr | e= paren_arith_expr )
            int alt18=4;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt18=1;
                }
                break;
            case IDENTIFIER:
                {
                int LA18_2 = input.LA(2);

                if ( (LA18_2==13) ) {
                    alt18=3;
                }
                else if ( (LA18_2==EOF||LA18_2==12||LA18_2==14||LA18_2==22||LA18_2==26||(LA18_2>=28 && LA18_2<=29)||(LA18_2>=32 && LA18_2<=37)||(LA18_2>=40 && LA18_2<=44)) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return value;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;
                }
                }
                break;
            case 39:
                {
                alt18=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return value;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:154:7: INTEGER
                    {
                    INTEGER7=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_base_arith_expr1330); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new NumExpr(Integer.parseInt(INTEGER7.getText())); 
                    }

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:155:7: IDENTIFIER
                    {
                    IDENTIFIER8=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_base_arith_expr1342); if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = new IdExpr(IDENTIFIER8.getText()); 
                    }

                    }
                    break;
                case 3 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:156:7: e= array_arith_expr
                    {
                    pushFollow(FOLLOW_array_arith_expr_in_base_arith_expr1356);
                    e=array_arith_expr();

                    state._fsp--;
                    if (state.failed) return value;
                    if ( state.backtracking==0 ) {
                       value = e; 
                    }

                    }
                    break;
                case 4 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:157:7: e= paren_arith_expr
                    {
                    pushFollow(FOLLOW_paren_arith_expr_in_base_arith_expr1368);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:160:1: array_arith_expr returns [ArithExpr value] : IDENTIFIER '[' e= arith_expr ']' ;
    public final ArithExpr array_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        Token IDENTIFIER9=null;
        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:161:5: ( IDENTIFIER '[' e= arith_expr ']' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:161:7: IDENTIFIER '[' e= arith_expr ']'
            {
            IDENTIFIER9=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_array_arith_expr1395); if (state.failed) return value;
            match(input,13,FOLLOW_13_in_array_arith_expr1397); if (state.failed) return value;
            pushFollow(FOLLOW_arith_expr_in_array_arith_expr1401);
            e=arith_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,14,FOLLOW_14_in_array_arith_expr1403); if (state.failed) return value;
            if ( state.backtracking==0 ) {
              value = new ArrayExpr(IDENTIFIER9.getText(), e);
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
    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:164:1: paren_arith_expr returns [ArithExpr value] : '(' e= arith_expr ')' ;
    public final ArithExpr paren_arith_expr() throws RecognitionException {
        ArithExpr value = null;

        ArithExpr e = null;


        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:165:5: ( '(' e= arith_expr ')' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:165:7: '(' e= arith_expr ')'
            {
            match(input,39,FOLLOW_39_in_paren_arith_expr1431); if (state.failed) return value;
            pushFollow(FOLLOW_arith_expr_in_paren_arith_expr1435);
            e=arith_expr();

            state._fsp--;
            if (state.failed) return value;
            match(input,40,FOLLOW_40_in_paren_arith_expr1437); if (state.failed) return value;
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

    // $ANTLR start synpred15_TheLang
    public final void synpred15_TheLang_fragment() throws RecognitionException {   
        BoolExpr e = null;


        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:103:4: ( '|' e= mid_bool_expr )
        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:103:4: '|' e= mid_bool_expr
        {
        match(input,28,FOLLOW_28_in_synpred15_TheLang743); if (state.failed) return ;
        pushFollow(FOLLOW_mid_bool_expr_in_synpred15_TheLang747);
        e=mid_bool_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_TheLang

    // $ANTLR start synpred16_TheLang
    public final void synpred16_TheLang_fragment() throws RecognitionException {   
        BoolExpr e = null;


        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:109:3: ( '&' e= base_bool_expr )
        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:109:3: '&' e= base_bool_expr
        {
        match(input,29,FOLLOW_29_in_synpred16_TheLang779); if (state.failed) return ;
        pushFollow(FOLLOW_base_bool_expr_in_synpred16_TheLang785);
        e=base_bool_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred16_TheLang

    // $ANTLR start synpred18_TheLang
    public final void synpred18_TheLang_fragment() throws RecognitionException {   
        BoolExpr b = null;


        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:115:7: (b= paren_bool_expr )
        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:115:7: b= paren_bool_expr
        {
        pushFollow(FOLLOW_paren_bool_expr_in_synpred18_TheLang828);
        b=paren_bool_expr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred18_TheLang

    // Delegated rules

    public final boolean synpred18_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_TheLang_fragment(); // can never throw exception
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
    public static final BitSet FOLLOW_level_in_base_declaration169 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_base_declaration177 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_base_declaration179 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_base_declaration182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_base_declaration199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INTEGER_in_base_declaration201 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_base_declaration203 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_base_declaration205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_base_declaration239 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_base_declaration241 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_base_declaration244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_base_declaration261 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INTEGER_in_base_declaration263 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_base_declaration265 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_base_declaration267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_level302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_level309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_base_statement_in_statement329 = new BitSet(new long[]{0x00000000023D8812L});
    public static final BitSet FOLLOW_base_statement_in_statement349 = new BitSet(new long[]{0x00000000023D8812L});
    public static final BitSet FOLLOW_assignStmt_in_base_statement379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_skipStmt_in_base_statement393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_readStmt_in_base_statement407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_writeStmt_in_base_statement421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStmt_in_base_statement435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_base_statement449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_assignStmt473 = new BitSet(new long[]{0x0000000000022000L});
    public static final BitSet FOLLOW_17_in_assignStmt476 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_assignStmt480 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_assignStmt482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_assignStmt490 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_assignStmt496 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_assignStmt498 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_assignStmt499 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_assignStmt503 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_assignStmt505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_skipStmt523 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_skipStmt525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_readStmt577 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_readStmt579 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_readStmt582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_readStmt590 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_readStmt596 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_readStmt598 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_readStmt600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_writeStmt622 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_writeStmt628 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_writeStmt630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ifStmt647 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_ifStmt651 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ifStmt653 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_ifStmt657 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ifStmt662 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_ifStmt666 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ifStmt668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_whileStmt702 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_whileStmt706 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_whileStmt708 = new BitSet(new long[]{0x00000000023D8810L});
    public static final BitSet FOLLOW_statement_in_whileStmt712 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_whileStmt714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mid_bool_expr_in_bool_expr736 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_bool_expr743 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_mid_bool_expr_in_bool_expr747 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_base_bool_expr_in_mid_bool_expr773 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_mid_bool_expr779 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_base_bool_expr_in_mid_bool_expr785 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_not_bool_expr_in_base_bool_expr813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_bool_expr_in_base_bool_expr828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_base_bool_expr839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_base_bool_expr868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr898 = new BitSet(new long[]{0x0000003F00000000L});
    public static final BitSet FOLLOW_32_in_base_bool_expr902 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_base_bool_expr933 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_base_bool_expr964 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_base_bool_expr996 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_base_bool_expr1027 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_base_bool_expr1059 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_base_bool_expr1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_not_bool_expr1091 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_not_bool_expr1097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_paren_bool_expr1120 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_bool_expr_in_paren_bool_expr1124 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_paren_bool_expr1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mult_div_arith_expr_in_arith_expr1152 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_41_in_arith_expr1170 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_mult_div_arith_expr_in_arith_expr1174 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_42_in_arith_expr1187 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_mult_div_arith_expr_in_arith_expr1191 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1219 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_43_in_mult_div_arith_expr1237 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1241 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_44_in_mult_div_arith_expr1254 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_un_min_arith_expr_in_mult_div_arith_expr1258 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_base_arith_expr_in_un_min_arith_expr1290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_un_min_arith_expr1302 = new BitSet(new long[]{0x0000008000000030L});
    public static final BitSet FOLLOW_base_arith_expr_in_un_min_arith_expr1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_base_arith_expr1330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_base_arith_expr1342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_array_arith_expr_in_base_arith_expr1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_arith_expr_in_base_arith_expr1368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_array_arith_expr1395 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_array_arith_expr1397 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_array_arith_expr1401 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_array_arith_expr1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_paren_arith_expr1431 = new BitSet(new long[]{0x0000048000000030L});
    public static final BitSet FOLLOW_arith_expr_in_paren_arith_expr1435 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_paren_arith_expr1437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_synpred15_TheLang743 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_mid_bool_expr_in_synpred15_TheLang747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_synpred16_TheLang779 = new BitSet(new long[]{0x000004C0C0000030L});
    public static final BitSet FOLLOW_base_bool_expr_in_synpred16_TheLang785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_bool_expr_in_synpred18_TheLang828 = new BitSet(new long[]{0x0000000000000002L});

}