grammar Latex;


//PARSER RULES
document	:	preamble body ;

preamble	:	docclass usepkg* docinfo? docinfo? docinfo? ;

docclass	:	KW_DOCUMENTCLASS classopt? CLASSARG ;

classopt	:	CLASSOPT ;

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

content		:	docinfo? docinfo? docinfo? inserttitle? anything* ;

anything	:	(text | environment | section) ;

inserttitle	:	KW_MAKETITLE ;

text		:	(string | expr | quote | specialchar)+ ;

string		:	STRING ;

expr		:	command ;

command		:	KW_PARAGRAPH | KW_NEWLINE | KW_SLASH | KW_TEXTBACKSLASH | KW_LDOTS | KW_CELSIUS | KW_EURO | underline | emph | bold | KW_TODAY | greek ;

underline	:	KW_UNDERLINE arg ;

emph		:	KW_EMPH arg ;

bold		:	KW_BOLD	arg ;

greek		:	KW_GREEK_CGAMMA | KW_GREEK_CDELTA | KW_GREEK_CTHETA | KW_GREEK_CLAMBDA | KW_GREEK_CPI
				| KW_GREEK_CSIGMA | KW_GREEK_CPHI | KW_GREEK_CPSI | KW_GREEK_COMEGA | KW_GREEK_ALFA | KW_GREEK_BETA
				| KW_GREEK_GAMMA | KW_GREEK_DELTA | KW_GREEK_EPSILON | KW_GREEK_ETA | KW_GREEK_THETA | KW_GREEK_IOTA
				| KW_GREEK_KAPPA | KW_GREEK_LAMBDA | KW_GREEK_MU | KW_GREEK_NU | KW_GREEK_XI | KW_GREEK_PI | KW_GREEK_RHO
				| KW_GREEK_SIGMA | KW_GREEK_TAU | KW_GREEK_UPSILON | KW_GREEK_PHI | KW_GREEK_CHI | KW_GREEK_PSI | KW_GREEK_OMEGA ;
				
environment	:	beginenv envcontent endenv ;

envcontent	:	(text | item)+ | table ;

beginenv	:	KW_BEGIN ENVARG arg? ;

endenv		:	KW_END ENVARG ;

item		:	KW_ITEM text ;

table		:	KW_HLINE row+ ;

row			:	cell (NEWCELL cell)* NEWROW KW_HLINE ;

cell		:	text ;

quote		:	QUOTE ;

specialchar	:	HASH | DOLAR | PERCENT | CARET | AMPERSAND | UNDERSCORE | CH_LCB | CH_RCB | TILDE | ENDASH | EMDASH | HYPHEN ;

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
KW_BEGIN		:	'\\begin' ;
KW_END			:	'\\end' ;
KW_PARAGRAPH	:	'\\par' ;
KW_NEWLINE		:	'\\newline' ;
KW_MAKETITLE	:	'\\maketitle' ;
KW_SECTION		:	'\\section' ;
KW_SUBSECTION	:	'\\subsection' ;
KW_SUBSUBSECTION:	'\\subsubsection' ;
KW_UNDERLINE	:	'\\underline' ;
KW_EMPH			:	'\\emph' ;
KW_BOLD			:	'\\textbf' ;
KW_ITEM			:	'\\item' ;
KW_HLINE		:	'\\hline' ;
KW_CT_NAME		:	'article' ;
KW_PAPERTYPE	:	'a4paper' ;
KW_DOCUMENT		:	'document' ;
KW_ENV_NAME		:	'itemize' | 'enumerate' | 'flushleft' | 'flushright' | 'center' | 'quote' | 'verbatim' | 'tabular';
KW_TODAY		:	'\\today{}' ;
KW_SLASH		:	'\\slash{}' ;
KW_TEXTBACKSLASH:	'\\textbackslash{}' ;
KW_LDOTS		:	'\\ldots{}' ;
KW_CELSIUS		:	'\\textcelsius{}' ;
KW_EURO			:	'\\texteuro{}' ;
KW_GREEK_CGAMMA	:	'\\textGamma{}' ;
KW_GREEK_CDELTA	:	'\\textDelta{}' ;
KW_GREEK_CTHETA	:	'\\textTheta{}' ;
KW_GREEK_CLAMBDA:	'\\textLambda{}' ;
KW_GREEK_CPI	:	'\\textPi{}' ;
KW_GREEK_CSIGMA	:	'\\textSigma{}' ;
KW_GREEK_CPHI	:	'\\textPhi{}' ;
KW_GREEK_CPSI	:	'\\textPsi{}' ;
KW_GREEK_COMEGA	:	'\\textOmega{}' ;
KW_GREEK_ALFA	:	'\\textalpha{}' ;
KW_GREEK_BETA	:	'\\textbeta{}' ;
KW_GREEK_GAMMA	:	'\\textgamma{}' ;
KW_GREEK_DELTA	:	'\\textdelta{}' ;
KW_GREEK_EPSILON:	'\\textepsilon{}' ;
KW_GREEK_ETA	:	'\\texteta{}' ;
KW_GREEK_THETA	:	'\\texttheta{}' ;
KW_GREEK_IOTA	:	'\\textiota{}' ;
KW_GREEK_KAPPA	:	'\\textkappa{}' ;
KW_GREEK_LAMBDA	:	'\\textlambda{}' ;
KW_GREEK_MU		:	'\\textmugreek{}' ;
KW_GREEK_NU		:	'\\textnu{}' ;
KW_GREEK_XI		:	'\\textxi{}' ;
KW_GREEK_PI		:	'\\textpi{}' ;
KW_GREEK_RHO	:	'\\textrho{}' ;
KW_GREEK_SIGMA	:	'\\textsigma{}' ;
KW_GREEK_TAU	:	'\\texttau{}' ;
KW_GREEK_UPSILON:	'\\textupsilon{}' ;
KW_GREEK_PHI	:	'\\textphi{}' ;
KW_GREEK_CHI	:	'\\textchi{}' ;
KW_GREEK_PSI	:	'\\textpsi{}' ;
KW_GREEK_OMEGA	:	'\\textomega{}' ;

//special
LSB				:	'[' ;
RSB				:	']' ;
LCB				:	'{' ;
RCB				:	'}' ;
NEWCELL			:	'&' ;
NEWROW			:	'\\\\' ;
COMMA			:	',' ;
HYPHEN			:	'-' ;
ENDASH			:	'--' ;
EMDASH			:	'---' ;
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
STRING			:	~[\[\]{}\\&%`'\t\r\n-]+ ;
CLASSOPT		:	LSB FONTSIZE? (COMMA KW_PAPERTYPE)? RSB ;
CLASSARG		:	LCB KW_CT_NAME RCB ;
DOCARG			:	LCB KW_DOCUMENT RCB ;
ENVARG			:	LCB KW_ENV_NAME RCB ;
OPT				:	LSB .*? RSB ;
ARG				:	LCB .*? RCB ;