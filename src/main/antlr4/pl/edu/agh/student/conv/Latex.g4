grammar Latex;


//PARSER RULES
document	:	preamble body ;

preamble	:	docclass usepkg* docinfo? docinfo? docinfo? ;

docclass	:	KW_DOCUMENTCLASS CLASSOPT? CLASSARG ;

usepkg		:	KW_USEPKG opt? arg;

arg			:	ARG ;

opt			:	OPT ;

docinfo		:	(authorinfo | titleinfo | dateinfo) ;

authorinfo	:	KW_DOCAUTHOR arg ;

titleinfo	:	KW_DOCTITLE arg ;

dateinfo	:	KW_DOCDATE arg ;

body		:	begindoc content enddoc ;

begindoc	:	KW_BEGIN DOCARG ;

enddoc		:	KW_END DOCARG ;

content		:	docinfo? docinfo? docinfo? inserttitle? anything ;

anything	:	(text | environment | section)* ;

inserttitle	:	KW_MAKETITLE ;

text		:	(string | expr | quote | specialchar)+ ;

string		:	STRING ;

expr		:	command ;

command		:	KW_NEWLINE | NEWLINE | KW_SLASH | KW_TEXTBACKSLASH | KW_LDOTS | underline | emph | KW_TODAY;

underline	:	KW_UNDERLINE arg ;

emph		:	KW_EMPH arg ;

environment	:	beginenv envcontent endenv ;

envcontent	:	(text | item)+ ;

beginenv	:	KW_BEGIN ENVARG ;

endenv		:	KW_END ENVARG ;

item		:	KW_ITEM text* ;

quote		:	QUOTE ;

specialchar	:	HASH | DOLAR | PERCENT | CARET | AMPERSAND | UNDERSCORE | CH_LCB | CH_RCB | TILDE ;

section		:	KW_SECTION arg (text | environment | subsection)* ;

subsection	:	KW_SUBSECTION arg (text | environment | subsubsection)* ;

subsubsection:	KW_SUBSUBSECTION arg (text | environment)* ;


//LEXER RULES

//keywords
KW_DOCUMENTCLASS:	'\\documentclass' ;
KW_USEPKG		:	'\\usepackage' ;
KW_DOCAUTHOR	:	'\\author' ;
KW_DOCTITLE		:	'\\title' ;
KW_DOCDATE		:	'\\date' ;
KW_TODAY		:	'\\today' ;
KW_BEGIN		:	'\\begin' ;
KW_END			:	'\\end' ;
KW_NEWLINE		:	'\\newline' ;
KW_SLASH		:	'\\slash' ;
KW_TEXTBACKSLASH:	'\\textbackslash' ;
KW_LDOTS		:	'\\ldots' ;
KW_MAKETITLE	:	'\\maketitle' ;
KW_SECTION		:	'\\section' ;
KW_SUBSECTION	:	'\\subsection' ;
KW_SUBSUBSECTION:	'\\subsubsection' ;
KW_UNDERLINE	:	'\\underline' ;
KW_EMPH			:	'\\emph' ;
KW_ITEM			:	'\\item' ;
KW_CT_NAME		:	'article' ;
KW_PAPERTYPE	:	'a4paper' ;
KW_DOCUMENT		:	'document' ;
KW_ENV_NAME		:	'itemize' | 'enumarate' | 'flushleft' | 'flushright' | 'center' ;

//special
LSB				:	'[' ;
RSB				:	']' ;
LCB				:	'{' ;
RCB				:	'}' ;
NEWLINE			:	'\\\\' ;
COMMA			:	',' ;
OQM				:	'``' ;
CQM				:	'\'\'' ;
HASH			:	'\\#' ;
DOLAR			:	'\\$' ;
PERCENT			:	'\\%' ;
CARET			:	'\\^{}' ;
AMPERSAND		:	'\\&' ;
UNDERSCORE		:	'\\_' ;
CH_LCB			:	'\\{' ;
CH_RCB			:	'\\}' ;
TILDE			:	'\\~{}' ;

//others
LINE_COMMENT	:   '%' .*? '\n' -> skip ;
WS				:	[ \t\r\n]+ -> skip ;
FONTSIZE		:	[0-9]? [0-9] 'pt' ;
QUOTE			:	OQM .*? CQM ;
STRING			:	~[\[\]{}\\%`'\t\r\n]+ ;
CLASSOPT		:	LSB FONTSIZE? (COMMA KW_PAPERTYPE)? RSB ;
CLASSARG		:	LCB KW_CT_NAME RCB ;
DOCARG			:	LCB KW_DOCUMENT RCB ;
ENVARG			:	LCB KW_ENV_NAME RCB ;
OPT				:	LSB .*? RSB ;
ARG				:	LCB .*? RCB ;
