lexer grammar DartLexer;


CONST:'const';
SINGLE_QUOTE
    :'\''
    ;

SLASH:'/';

WHITESPACE:   [ \t\r\n] ->skip;

CHILD:'child';

COLOR:'color';

COLUMN:'Column';

WIDGET: 'Widget';

MAIN: 'main';

VALUE: 'value';

PUT: 'put';

FIND: 'find';

OBX: 'Obx';

RUNAPP:'runApp';

ROW:'Row';

CONTAINER:'Container' ;

EXPANDED:'Expanded' ;

PADDING:'Padding';

PADD:'padding';

SCAFFOLD:'Scaffold';

BODY:'body:';

IMAGE:'Image.network';

DECORATION:'decoration:';

BORDER:'border:'WHITESPACE*'Border.all' COMMA?;

BORDERRADIUS:'borderRadius';

BORDERRADIUSVALUE:'BorderRadius.all'RIGHTKAOS NUM LEFTKAOS ;

CHILDREN:'children:'CONST?;

TEXT:'Text';

MATERIALBUTTON:'MaterialButton';

CENTER:'Center';

SIZEDBOX:'SizedBox';

APPBARWIDGET:'AppBar';

BACK:'back';

ICON:'Icon';

ICONS:'Icons';

APPBAR:'appBar';

HEIGHT:'height:';

WIDTH:'width:';

MAINAXIS:'mainAxisAlignment:'WHITESPACE*'MainAxisAlignment.'('center'|'start'|'end') WHITESPACE*',';

CROSSAXIS:'crossAxisAlignment:'WHITESPACE*'CrossAxisAlignment.'('center'|'start'|'end') WHITESPACE*',';

ONPRESSED:'onPressed:';

STATELESS:'StatelessWidget';

STATEFUL:'StatefulWidget';

SETSTATE:'setState';//update

CLAS:'class ';

NUM: '0' |'-'?[1-9][0-9]*;

EXTENDS:'extends';

EQUAL:'=';

RETURN:'return';

BUILD:'build';

STATE :'State';

HOME:'home:'' ';

GET:
    'Get'
    ;

TO:
    'to'
    ;

OVERRIDEWORD:'@override';

BUILDCONTEXT:'(BuildContext context)';

MATERIALAPP:'MaterialApp'|'GetMaterialApp';

DEBUG:'debugShowCheckedModeBanner:';

TITLE:'title';

THEME:'theme:';

THEMEDATA:'ThemeData';

PRIMARYSWATCH: 'primarySwatch';

KEYCAPITAL:'Key';

KEYSMALL:'key';

FINAL:'final';

SUPER:'super';

IMPORT:'import';

PACKAGE:'package';

LIST:'List'| 'List<String>'
;

URL :'https://'(VARIABLE'.')* VARIABLE '/'NUM'?'VARIABLE EQUAL NUM;


//------------------------------------------------attchars

COMMA:',';

RIGHTKAOS:'(';

LEFTKAOS:')';

RIGHTSQUARE:'[';

LEFTSQUARE:']';

RIGHTCURLY:'{';

LEFTCURLY:'}';

REQUIRED:'required';

THIS:'this';

TWOPOINTSABOVEEACHOTHER:':';

FLUTTER:'flutter';

DART:'dart';

SEMICOLON:';';

QUESTIONMARK:'?';

DOT:'.';

OPEN_C:'<';

CLOSE_C:'>';

DOLAR:'$';


//----------------------types



INT:'int';

RXINT:'Rx<int>';

OBS:'obs';

STRING:'String';

GETXCONTROLLER:'GetxController';

VOID:'void';

BOOLEAN:'bool';

FALSE:'false';

TRUE:'true';



//-------------------------------------------------------------------modes

CREATESTATE:'createState';

EDGEINSETS:'EdgeInsets.all';

COLORS:'Colors';

COLORVALUE:'red'|'blue'|'black'|'white'|'amberAccent'|'lightGreen'|'lightGreenAccent';

BOXDECORATION: 'BoxDecoration';

COMPOUNDASSIGMENTOPERATOR:
		'*='
	|	'/='
	|	'%='
	|	'+='
	|	'-=' ;


MULTI_LINE_COMMENT:
		'/*' (MULTI_LINE_COMMENT | .)*? '*/'
		 -> skip
	;

SINGLE_LINE_COMMENT:
		'//' (~('\r' | '\n'))* WHITESPACE?
		 -> skip
	;



//COMPARE:
//   ;

INCREAMENTOPERATOR:
		'++'
	|	'--'
	;

MULTIPLICATIVEOPERATOR:
		'*'
	|	SLASH
	;

ADDITIVEOPERATOR:
		'+'
	|	'-'
	;
DIVISIONOPERATOR: SLASH;
MINUSOPERATOR: '-';

ICONSVALUE:
        'minimize'
     |  'add'
     ;

EQUALITYOPERATOR:
		'=='
	|	'!='
	;
ANDOR:
'&&'
|'||';

WHILE:
		'while'
	;
	PRINT:'print';
TRY:
    		'try'
    	;
VAR:
    		'var'
    	;
SWITCH:
		'switch'
	;
FOR:
		'for'
	;
IF:
		'if'
	;
ELSE:
		'else'
	;
ENUM:
		'enum'
	;
DO:
		'do'
	;
BREAK:
		'break'
	;
CASE:
		'case'
	;
CATCH:
		'catch'
	;
CLASS:
		'class'
	;


 DEFAULT:'default';

STYLE:'style';
TEXTSTYLE:'TextStyle';
FONTSIZE:'fontSize';
FUNCTION: 'function';
OPERATOR:
		'operator'
	;

STRING_LINE:
 '"' ( ~[\\"] | '\\' . )* '"'
  |'\'' ( ~[\\"] | '\\' . )* '\''
 ;

VARIABLE :
[a-zA-Z_0-9]+
;
