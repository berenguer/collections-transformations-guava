M2 IAGL Lille 1 - OPL (OPTIMISATIONS POUR LOGICIEL)

Migrator of JDK collections to Google Guave, with Spoon
====================

Authors
---------------------
Maxime Chaste
Pierre-Philippe Berenguer

Spoon
---------------------
We assume you have some knowledges about Spoon.
See :
Spoon librairy : Java code analysis and transformation
or
[link](http://spoon.gforge.inria.fr/)

Guava
---------------------
[link](https://code.google.com/p/guava-libraries/)

Overview
---------------------
rapport.pdf
	presentation of the project

*/opl_spoon*
	project parent directory

	*/input*
		simple class files used as input to the Spoon.Launcher;
	*/processor*
		package
			* contains CtVariableProcessorDispatcher.class. It is the Spoon Processor for CtVariable (Spoon object).
			* it dispatch transformations to different Replacer objects.
			* a Replacer transform an unique specific JDK object (list, arraylist...)

			* PatternProcessor is the first attempt to transform multiple lines of code using an object from collection.
			only "add(object)" as consecutive invocations for an array. All "add" invocations are replace by only one line invocation.
			
	*/spooned*
		result of transformation when running our Spoon Processor
	*/src*
		empty : just for satisfy eclipse
	
	
