package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
	        if(p.errorDetected) {
	        	log.error("NEUSPESNO SINTAKSNO PARSIRANJE");
	        	return;
	        }
	       // Tab.init();
			// ispis sintaksnog stabla
			//log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticPassVisitor.init();
			SemanticPassVisitor v = new SemanticPassVisitor();
			prog.traverseBottomUp(v); 
			
			Tab.dump();
			
			if(!v.isSuccess()) {
				log.error("NEUSPESNO SEMANTICKO  PARSIRANJE");
				
			} else {
				
				log.info("USPESNO SEMANTICKO PARSIRANJE");
				File file = new File("test/output.obj");
				if(file.exists()) file.delete();
				Code.dataSize = v.getGlobalVariableNumber();
				CodeGenerator codeGen = new CodeGenerator();
				prog.traverseBottomUp(codeGen);
				Code.write(new FileOutputStream(file));
				
				String [] argS1 = {"test/output.obj", "-debug"};
				String [] argS = {"test/output.obj"};
						
			 disasm.main(argS);
				Run.main(argS);
			

				//Tab.dump( new SymbolTablePrinter());
					
				
			}
			
				
			
	/*      
			log.info(" Print count calls = " + v.printCallCount);

			log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);
			
			log.info("===================================");
			//Tab.dump();
			
			if(!p.errorDetected && v.passed()){
				log.info("Parsiranje uspesno zavrseno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			} */
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
