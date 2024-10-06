parser grammar DartParser;
options { tokenVocab=DartLexer; }


prog:imports* main widgetclass(classes)*;

imports:IMPORT STRING_LINE  SEMICOLON;

//-------------main function

main:type MAIN RIGHTKAOS LEFTKAOS RIGHTCURLY (declare|statement)*
            runappfunction (declare|statement)* LEFTCURLY;

runappfunction:RUNAPP RIGHTKAOS CONST istedaaaclass LEFTKAOS SEMICOLON;

istedaaaclass: VARIABLE  RIGHTKAOS  LEFTKAOS;
callfunction: VARIABLE RIGHTKAOS  VARIABLE? (COMMA  VARIABLE)* LEFTKAOS SEMICOLON;

//-------------classes

widgetclass:stlessclass|stfulclass;

stlessclass:CLAS VARIABLE EXTENDS   STATELESS   RIGHTCURLY stlessclassbody LEFTCURLY LEFTCURLY ;
stlessclassbody: constructor?  (def)* (callfunction)* buildfunction (def)*  ;

stfulclass:CLAS VARIABLE EXTENDS   STATEFUL   RIGHTCURLY constructor?
             (def)* (callfunction)* createstate     LEFTCURLY   stateclass
             (def)*   LEFTCURLY ;

stateclass: CLAS VARIABLE EXTENDS
            STATE OPEN_C VARIABLE CLOSE_C   RIGHTCURLY
            (def)* buildfunction   (def)*   LEFTCURLY;

classes:controllerClass|regularclass|widgetclass;


regularclass:CLAS VARIABLE (EXTENDS VARIABLE)?  RIGHTCURLY (def)* (callfunction)*  constructor?  (def)* LEFTCURLY;
controllerClass:CLAS VARIABLE EXTENDS GETXCONTROLLER  RIGHTCURLY (def)* (callfunction)* constructor? (def)* LEFTCURLY;

//--------------declaration
declare:list|definevariable|defineRXvariable;

defineRXvariable:RXINT VARIABLE EQUAL NUM DOT OBS SEMICOLON;

definevariable: FINAL? type VARIABLE (EQUAL (STRING_LINE | NUM)?)? SEMICOLON ;

list:LIST VARIABLE  (EQUAL RIGHTSQUARE STRING_LINE( COMMA STRING_LINE )*)? COMMA? LEFTSQUARE? SEMICOLON;


statement:
		whileStatement
	|	ifStatement
	|	switchStatement
	|	tryStatement
	|   block
	|   print
	|   navigation
	|   setstate
	|   (expr SEMICOLON)
	|   expression
	|   changingvalue
	|   controllerFunctions
	|   controllerAttributes
	;


controllerFunctions:VARIABLE DOT VARIABLE RIGHTKAOS (VARIABLE|NUM) LEFTKAOS SEMICOLON;

controllerAttributes:VARIABLE DOT VARIABLE SEMICOLON;

controllerPutFind: (VARIABLE VARIABLE EQUAL GET DOT PUT RIGHTKAOS istedaaaclass LEFTKAOS SEMICOLON)
                    |(FINAL VARIABLE  EQUAL GET DOT FIND OPEN_C VARIABLE CLOSE_C RIGHTKAOS  LEFTKAOS SEMICOLON) ;


block:RIGHTCURLY (statement|def)*   (RETURN (VARIABLE| NUM)?SEMICOLON)? LEFTCURLY;

changingvalue:VARIABLE (DOT VALUE)? INCREAMENTOPERATOR SEMICOLON
              |VARIABLE (COMPOUNDASSIGMENTOPERATOR|EQUAL) expr SEMICOLON
              |VARIABLE EQUAL STRING_LINE SEMICOLON;

 print:
 PRINT RIGHTKAOS (STRING_LINE|VARIABLE) LEFTKAOS SEMICOLON ;

tryStatement:
		TRY block catchPart block
	;
catchPart:
		CATCH RIGHTKAOS VARIABLE (COMMA VARIABLE)? LEFTKAOS
	;

switchStatement:
		SWITCH RIGHTKAOS expression LEFTKAOS RIGHTCURLY switchCase* defaultCase? LEFTCURLY
	;
switchCase:
	 CASE expression TWOPOINTSABOVEEACHOTHER  statement  BREAK SEMICOLON
	;

defaultCase:
   DEFAULT TWOPOINTSABOVEEACHOTHER  statement  BREAK SEMICOLON
	;

whileStatement:
		WHILE RIGHTKAOS expression LEFTKAOS block
	;

ifStatement:
		IF RIGHTKAOS expression (ANDOR expression)* LEFTKAOS ifPart=block (ELSE elsePart=block)?
	;

def:function|declare|controllerPutFind;

type:INT|STRING|BOOL|VOID;

function: type  VARIABLE RIGHTKAOS functionparameter* LEFTKAOS RIGHTCURLY (statement|declare)*   (RETURN (VARIABLE| NUM)?SEMICOLON)? LEFTCURLY;

functionparameter:type VARIABLE (COMMA type VARIABLE)*  ;

assignmentOperator:
	// EQUAL
	|EQUALITYOPERATOR
	|COMPOUNDASSIGMENTOPERATOR
	|( CLOSE_C
          |OPEN_C
          |CLOSE_C EQUAL
          |OPEN_C EQUAL)
	;

expression:
    	VARIABLE assignmentOperator expression
    |	expr assignmentOperator expression
    |   VARIABLE
    |   NUM
    ;

 expr:
          expr MULTIPLICATIVEOPERATOR expr
        | expr ADDITIVEOPERATOR expr
        |VARIABLE   (RIGHTSQUARE VARIABLE LEFTSQUARE)?
        |NUM
        ;


buildfunction:OVERRIDEWORD  WIDGET BUILD BUILDCONTEXT RIGHTCURLY RETURN   widget  SEMICOLON ;

constructor:(CONST? VARIABLE RIGHTKAOS RIGHTCURLY KEYCAPITAL QUESTIONMARK
            KEYSMALL LEFTCURLY LEFTKAOS  TWOPOINTSABOVEEACHOTHER  SUPER
             RIGHTKAOS KEYSMALL TWOPOINTSABOVEEACHOTHER KEYSMALL LEFTKAOS SEMICOLON)|
            (CONST? VARIABLE RIGHTKAOS RIGHTCURLY SUPER DOT  KEYSMALL
             (COMMA REQUIRED THIS DOT VARIABLE)* LEFTCURLY LEFTKAOS  SEMICOLON );

createstate:OVERRIDEWORD   STATE
            OPEN_C VARIABLE CLOSE_C
             CREATESTATE RIGHTKAOS  LEFTKAOS EQUAL CLOSE_C
             VARIABLE RIGHTKAOS  LEFTKAOS SEMICOLON;

extends:EXTENDS   stateWidget;


navigation:GET DOT (TO RIGHTKAOS istedaaaclass LEFTKAOS SEMICOLON|
            BACK RIGHTKAOS LEFTKAOS SEMICOLON) ;


materialtitle:TITLE  TWOPOINTSABOVEEACHOTHER STRING_LINE COMMA;

height:HEIGHT NUM ;

width:WIDTH NUM ;

materialtheme:THEME   themedata;

materialdebug: DEBUG   (TRUE|FALSE) COMMA;

themedata:THEMEDATA RIGHTKAOS
          PRIMARYSWATCH TWOPOINTSABOVEEACHOTHER
          COLORS DOT COLORVALUE  COMMA? LEFTKAOS COMMA ;

onpressed:ONPRESSED   RIGHTKAOS  LEFTKAOS   RIGHTCURLY statement* setstate? statement* LEFTCURLY COMMA?;

setstate: SETSTATE RIGHTKAOS setbody LEFTCURLY  LEFTKAOS SEMICOLON;
setbody:RIGHTKAOS  LEFTKAOS RIGHTCURLY  (statement)*
;

child: CHILD TWOPOINTSABOVEEACHOTHER widget COMMA? ;

//-----------------------------------------------------------widget



scaffold:SCAFFOLD RIGHTKAOS
    (APPBAR TWOPOINTSABOVEEACHOTHER
    APPBARWIDGET RIGHTKAOS
    (TITLE TWOPOINTSABOVEEACHOTHER
    text COMMA? )?LEFTKAOS
    COMMA)?
    BODY widget COMMA? LEFTKAOS  ;


materialapp:MATERIALAPP   RIGHTKAOS
                materialdebug?
                 materialtitle?   materialtheme?
                  HOME CONST? istedaaaclass COMMA? LEFTKAOS  ;

container:CONTAINER RIGHTKAOS
        (((height COMMA)?|(width COMMA?)?)|
        ((height) COMMA (width) COMMA?)|
        ((width)COMMA (height)COMMA?))
        (COLOR TWOPOINTSABOVEEACHOTHER
         COLORS DOT COLORVALUE COMMA?)?
         boxdecoration?
         (child)? LEFTKAOS ;

boxdecoration:DECORATION  BOXDECORATION RIGHTKAOS
        (((COLOR TWOPOINTSABOVEEACHOTHER
         COLORS DOT COLORVALUE)?
        ( BORDER RIGHTKAOS  LEFTKAOS )?)?)
        LEFTKAOS COMMA ;


num: NUM(DOT NUM+)?;


padding:PADDING RIGHTKAOS
        (PADD TWOPOINTSABOVEEACHOTHER CONST?
        EDGEINSETS RIGHTKAOS num LEFTKAOS  COMMA )? child  LEFTKAOS;


column:COLUMN RIGHTKAOS MAINAXIS? CROSSAXIS? CHILDREN RIGHTSQUARE
        widget  (COMMA widget)* COMMA? LEFTSQUARE COMMA? LEFTKAOS;

row:ROW RIGHTKAOS MAINAXIS? CROSSAXIS? CHILDREN RIGHTSQUARE
      widget (COMMA widget)* COMMA? LEFTSQUARE COMMA? LEFTKAOS ;

expanded:EXPANDED RIGHTKAOS
 child LEFTKAOS ;

text:CONST? TEXT RIGHTKAOS
     ((STRING_LINE)
    |((VARIABLE DOT)?VARIABLE(RIGHTSQUARE NUM LEFTSQUARE)?))
    COMMA?
    (STYLE TWOPOINTSABOVEEACHOTHER TEXTSTYLE RIGHTKAOS
    FONTSIZE TWOPOINTSABOVEEACHOTHER NUM LEFTKAOS COMMA?)?
    LEFTKAOS;




//todo: same as text for vaiables
image:IMAGE RIGHTKAOS  ((STRING_LINE)
                          |((VARIABLE DOT)?VARIABLE(RIGHTSQUARE NUM LEFTSQUARE)?))LEFTKAOS;


obx:OBX RIGHTKAOS RIGHTKAOS LEFTKAOS EQUAL CLOSE_C widget COMMA? LEFTKAOS;


sizedbox:CONST? SIZEDBOX RIGHTKAOS (((height COMMA)?|(width COMMA?)?)|((height) COMMA (width) COMMA?)|((width)COMMA (height)COMMA?))LEFTKAOS;


button:MATERIALBUTTON RIGHTKAOS   onpressed
 child LEFTKAOS ;



center:CENTER RIGHTKAOS child LEFTKAOS;


icon:ICON RIGHTKAOS ICONS DOT ICONSVALUE LEFTKAOS ;


widget:
materialapp|
button|
image|
scaffold|
column|
row|
container|
expanded|
padding|
center|
sizedbox|
text|
obx|
icon
;

stateWidget:
|STATEFUL
|STATELESS
 ;



