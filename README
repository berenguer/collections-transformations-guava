M2 IAGL - OPL (OPTIMISATIONS POUR LOGICIEL)
Subject : migrator JDK collections to Google Guave collections with Spoon

# -------------- AUTHORS -------------------
Maxime Chaste
Pierre-Philippe Berenguer

# ---------------- SPOON -------------------
Spoon librairy : Java code analysis and transformation
http://spoon.gforge.inria.fr/

# ---------------- GUAVA -------------------
https://code.google.com/p/guava-libraries/

# ------------- EXPLANATIONS ---------------
rapport.pdf

# --------------- OVERVIEW -----------------

/opl_spoon
	project parent directory

	/input
		simple class files used to be input to the Spoon.Launcher;
		they offer us to check transformations manually : comparing originals to spooned ones
	/processor
		package
			Contains CtVariableProcessorDispatcher.class. It is the Spoon Processor for CtVariable (Spoon).
			It dispatch transformation to different Replacer.

			PatternProcessor.class is the first attempt to transform multiple add(object) consecutiv for an array, into one line
	/spooned
		result of transformation when running our Processor with Spoon
	/src
		empty : just for satisfy eclipse
	
	
