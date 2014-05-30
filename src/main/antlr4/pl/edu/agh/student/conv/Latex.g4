grammar Latex;

//PARSER RULES
document	:	preamble body ;

preamble	:	docclass usepkg* docinfo? docinfo? docinfo? ;

docclass	:	KW_DOCUMENTCLASS classopts? classtype ;

classopts	:	LSB FONTSIZE? (COMMA KW_PAPERTYPE)? RSB;

classtype	:	LCB KW_CT_NAME RCB ;

usepkg		:	KW_USEPKG opt? arg ;

opt			:	LSB OPTIONS RCB ;

arg			:	LCB STRING RCB ;

docinfo		:	(authorinfo | titleinfo | dateinfo) ;

authorinfo	:	KW_DOCAUTHOR arg ;

titleinfo	:	KW_DOCTITLE arg ;

dateinfo	:	KW_DOCDATE arg ;

body		:	begindoc content enddoc ;

begindoc	:	KW_BEGIN LCB KW_DOCUMENT RCB ;

enddoc		:	KW_END LCB KW_DOCUMENT RCB ;

content		:	docinfo? docinfo? docinfo? inserttitle? (text | section)* ;

inserttitle	:	KW_MAKETITLE ;

text		:	(STRING | expr | environment | quote | specialchar)+ ;

expr		:	command arg? ;

command		:	KW_NEWLINE | KW_SLASH | KW_TEXTBACKSLASH | KW_LDOTS | KW_UNDERLINE | KW_EMPH ;

environment	:	KW_BEGIN LCB KW_ENV_NAME RCB (STRING | item)+ KW_END LCB KW_ENV_NAME RCB ;

item		:	KW_ITEM STRING* ;

quote		:	OQM STRING CQM ;

specialchar	:	HASH | DOLAR | PERCENT | CARET | AMPERSAND | UNDERSCORE | CH_LCB | CH_RCB | TILDE ;

section		:	KW_SECTION arg (text | subsection)* ;

subsection	:	KW_SUBSECTION arg (text | subsubsection)* ;

subsubsection:	KW_SUBSUBSECTION arg text* ;



//LEXER RULES

//keywords
KW_DOCUMENTCLASS:	'\\documentclass' ;
KW_USEPKG		:	'\\usepackage' ;
KW_DOCAUTHOR	:	'\\author' ;
KW_DOCTITLE		:	'\\title' ;
KW_DOCDATE		:	'\\date' ;
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
FONTSIZE		:	[0-9]? [0-9] 'pt' ;
STRING			:	'STRING' ;
OPTIONS			:	'OPTIONS' ;
LINE_COMMENT	:   '%' ~[\r\n]* -> skip ;
WS				:	[ \t\r\n]+ -> skip ;
