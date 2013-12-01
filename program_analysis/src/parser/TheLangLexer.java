// $ANTLR 3.3 Nov 30, 2010 12:45:30 E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g 2013-12-01 13:26:14

package parser;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TheLangLexer extends Lexer {
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

    public TheLangLexer() {;} 
    public TheLangLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TheLangLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:12:6: ( 'program' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:12:8: 'program'
            {
            match("program"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:13:7: ( 'end' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:13:9: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:14:7: ( 'int' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:14:9: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:15:7: ( ';' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:15:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:16:7: ( '[' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:16:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:17:7: ( ']' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:17:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:18:7: ( 'low' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:18:9: 'low'
            {
            match("low"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:19:7: ( 'high' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:19:9: 'high'
            {
            match("high"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:20:7: ( ':=' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:20:9: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:21:7: ( 'skip' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:21:9: 'skip'
            {
            match("skip"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:22:7: ( 'read' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:22:9: 'read'
            {
            match("read"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:23:7: ( 'write' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:23:9: 'write'
            {
            match("write"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:24:7: ( 'if' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:24:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:25:7: ( 'then' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:25:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:26:7: ( 'else' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:26:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:27:7: ( 'fi' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:27:9: 'fi'
            {
            match("fi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:28:7: ( 'while' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:28:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:29:7: ( 'do' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:29:9: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:30:7: ( 'od' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:30:9: 'od'
            {
            match("od"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:31:7: ( '|' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:31:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:32:7: ( '&' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:32:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:33:7: ( 'true' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:33:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:34:7: ( 'false' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:34:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:35:7: ( '=' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:35:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:36:7: ( '<=' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:36:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:37:7: ( '<' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:37:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:38:7: ( '>=' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:38:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:39:7: ( '>' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:39:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:40:7: ( '!=' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:40:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:41:7: ( '!' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:41:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:42:7: ( '(' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:42:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:43:7: ( ')' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:43:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:44:7: ( '+' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:44:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:45:7: ( '-' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:45:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:46:7: ( '*' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:46:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:47:7: ( '/' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:47:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:170:9: ( '(*' ( options {greedy=false; } : . )* '*)' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:170:11: '(*' ( options {greedy=false; } : . )* '*)'
            {
            match("(*"); 

            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:170:16: ( options {greedy=false; } : . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==')') ) {
                        alt1=2;
                    }
                    else if ( ((LA1_1>='\u0000' && LA1_1<='(')||(LA1_1>='*' && LA1_1<='\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0>='\u0000' && LA1_0<=')')||(LA1_0>='+' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:170:43: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match("*)"); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:9: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:11: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:11: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='0') ) {
                alt3=1;
            }
            else if ( ((LA3_0>='1' && LA3_0<='9')) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:12: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:18: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:27: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:173:27: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:175:12: ( LETTER ( LETTER | '0' .. '9' )* )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:175:14: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:175:21: ( LETTER | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:178:8: ( 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:183:4: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:183:6: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | COMMENT | INTEGER | IDENTIFIER | WS )
        int alt5=40;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:10: T__9
                {
                mT__9(); 

                }
                break;
            case 2 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:15: T__10
                {
                mT__10(); 

                }
                break;
            case 3 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:21: T__11
                {
                mT__11(); 

                }
                break;
            case 4 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:27: T__12
                {
                mT__12(); 

                }
                break;
            case 5 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:33: T__13
                {
                mT__13(); 

                }
                break;
            case 6 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:39: T__14
                {
                mT__14(); 

                }
                break;
            case 7 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:45: T__15
                {
                mT__15(); 

                }
                break;
            case 8 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:51: T__16
                {
                mT__16(); 

                }
                break;
            case 9 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:57: T__17
                {
                mT__17(); 

                }
                break;
            case 10 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:63: T__18
                {
                mT__18(); 

                }
                break;
            case 11 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:69: T__19
                {
                mT__19(); 

                }
                break;
            case 12 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:75: T__20
                {
                mT__20(); 

                }
                break;
            case 13 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:81: T__21
                {
                mT__21(); 

                }
                break;
            case 14 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:87: T__22
                {
                mT__22(); 

                }
                break;
            case 15 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:93: T__23
                {
                mT__23(); 

                }
                break;
            case 16 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:99: T__24
                {
                mT__24(); 

                }
                break;
            case 17 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:105: T__25
                {
                mT__25(); 

                }
                break;
            case 18 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:111: T__26
                {
                mT__26(); 

                }
                break;
            case 19 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:117: T__27
                {
                mT__27(); 

                }
                break;
            case 20 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:123: T__28
                {
                mT__28(); 

                }
                break;
            case 21 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:129: T__29
                {
                mT__29(); 

                }
                break;
            case 22 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:135: T__30
                {
                mT__30(); 

                }
                break;
            case 23 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:141: T__31
                {
                mT__31(); 

                }
                break;
            case 24 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:147: T__32
                {
                mT__32(); 

                }
                break;
            case 25 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:153: T__33
                {
                mT__33(); 

                }
                break;
            case 26 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:159: T__34
                {
                mT__34(); 

                }
                break;
            case 27 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:165: T__35
                {
                mT__35(); 

                }
                break;
            case 28 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:171: T__36
                {
                mT__36(); 

                }
                break;
            case 29 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:177: T__37
                {
                mT__37(); 

                }
                break;
            case 30 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:183: T__38
                {
                mT__38(); 

                }
                break;
            case 31 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:189: T__39
                {
                mT__39(); 

                }
                break;
            case 32 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:195: T__40
                {
                mT__40(); 

                }
                break;
            case 33 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:201: T__41
                {
                mT__41(); 

                }
                break;
            case 34 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:207: T__42
                {
                mT__42(); 

                }
                break;
            case 35 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:213: T__43
                {
                mT__43(); 

                }
                break;
            case 36 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:219: T__44
                {
                mT__44(); 

                }
                break;
            case 37 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:225: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 38 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:233: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 39 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:241: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 40 :
                // E:\\codes\\github\\ProgramAnalysis\\program_analysis\\src\\parser\\TheLang.g:1:252: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\3\36\3\uffff\2\36\1\uffff\7\36\3\uffff\1\62\1\64\1\66"+
        "\1\70\10\uffff\4\36\1\75\10\36\1\106\1\36\1\110\1\111\10\uffff\1"+
        "\36\1\113\1\36\1\115\1\uffff\1\116\7\36\1\uffff\1\36\2\uffff\1\36"+
        "\1\uffff\1\130\2\uffff\1\131\1\132\1\133\2\36\1\136\1\137\2\36\4"+
        "\uffff\1\142\1\143\2\uffff\1\144\1\36\3\uffff\1\146\1\uffff";
    static final String DFA5_eofS =
        "\147\uffff";
    static final String DFA5_minS =
        "\1\11\1\162\1\154\1\146\3\uffff\1\157\1\151\1\uffff\1\153\1\145"+
        "\2\150\1\141\1\157\1\144\3\uffff\3\75\1\52\10\uffff\1\157\1\144"+
        "\1\163\1\164\1\60\1\167\1\147\1\151\1\141\2\151\1\145\1\165\1\60"+
        "\1\154\2\60\10\uffff\1\147\1\60\1\145\1\60\1\uffff\1\60\1\150\1"+
        "\160\1\144\1\164\1\154\1\156\1\145\1\uffff\1\163\2\uffff\1\162\1"+
        "\uffff\1\60\2\uffff\3\60\2\145\2\60\1\145\1\141\4\uffff\2\60\2\uffff"+
        "\1\60\1\155\3\uffff\1\60\1\uffff";
    static final String DFA5_maxS =
        "\1\174\1\162\2\156\3\uffff\1\157\1\151\1\uffff\1\153\1\145\2\162"+
        "\1\151\1\157\1\144\3\uffff\3\75\1\52\10\uffff\1\157\1\144\1\163"+
        "\1\164\1\172\1\167\1\147\1\151\1\141\2\151\1\145\1\165\1\172\1\154"+
        "\2\172\10\uffff\1\147\1\172\1\145\1\172\1\uffff\1\172\1\150\1\160"+
        "\1\144\1\164\1\154\1\156\1\145\1\uffff\1\163\2\uffff\1\162\1\uffff"+
        "\1\172\2\uffff\3\172\2\145\2\172\1\145\1\141\4\uffff\2\172\2\uffff"+
        "\1\172\1\155\3\uffff\1\172\1\uffff";
    static final String DFA5_acceptS =
        "\4\uffff\1\4\1\5\1\6\2\uffff\1\11\7\uffff\1\24\1\25\1\30\4\uffff"+
        "\1\40\1\41\1\42\1\43\1\44\1\46\1\47\1\50\21\uffff\1\31\1\32\1\33"+
        "\1\34\1\35\1\36\1\45\1\37\4\uffff\1\15\10\uffff\1\20\1\uffff\1\22"+
        "\1\23\1\uffff\1\2\1\uffff\1\3\1\7\11\uffff\1\17\1\10\1\12\1\13\2"+
        "\uffff\1\16\1\26\2\uffff\1\14\1\21\1\27\1\uffff\1\1";
    static final String DFA5_specialS =
        "\147\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\37\1\uffff\2\37\22\uffff\1\37\1\26\4\uffff\1\22\1\uffff"+
            "\1\27\1\30\1\33\1\31\1\uffff\1\32\1\uffff\1\34\12\35\1\11\1"+
            "\4\1\24\1\23\1\25\2\uffff\32\36\1\5\1\uffff\1\6\1\uffff\1\36"+
            "\1\uffff\3\36\1\17\1\2\1\16\1\36\1\10\1\3\2\36\1\7\2\36\1\20"+
            "\1\1\1\36\1\13\1\12\1\15\2\36\1\14\3\36\1\uffff\1\21",
            "\1\40",
            "\1\42\1\uffff\1\41",
            "\1\44\7\uffff\1\43",
            "",
            "",
            "",
            "\1\45",
            "\1\46",
            "",
            "\1\47",
            "\1\50",
            "\1\52\11\uffff\1\51",
            "\1\53\11\uffff\1\54",
            "\1\56\7\uffff\1\55",
            "\1\57",
            "\1\60",
            "",
            "",
            "",
            "\1\61",
            "\1\63",
            "\1\65",
            "\1\67",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\107",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\112",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\114",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "",
            "\1\126",
            "",
            "",
            "\1\127",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\134",
            "\1\135",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\140",
            "\1\141",
            "",
            "",
            "",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            "\1\145",
            "",
            "",
            "",
            "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | COMMENT | INTEGER | IDENTIFIER | WS );";
        }
    }
 

}