package de.matt3o.bruchrechner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Matteo
 * @version 0.4
 */
public class Logger {
	/** Die zuletzt verwendete Logger Instance */
	private static Logger loggerInstanc;

	/** Der Path für die logFile. */
	private File logPath;

	/** Der FileWirter f&uuml;r die Log Datei */
	private FileWriter logOutput;

	/** Der FileWirter f&uuml;r die Error Log Datei. */
	private FileWriter logErrOutput;

	/** Maximale Logs die behalten werden. */
	private int keepMaxLogs = 3000000;

	/**
	 * Erzeugt eine neue Logger instanz.<br>
	 * Dabei wird automatisch eine LogFile sowie ErrorLogFile im StandartPath
	 * erzeugt.<br>
	 */
	private Logger() {
		if (System.getProperty("os.name").equals("Mac OS X")) {
			logPath = new File(System.getProperty("user.home")
					+ "/Library/Application Support/matt3o12/bruchrechner/logs");

		} else {
			logPath = new File(System.getProperty("user.home")
					+ System.getProperty("file.separator") + ".matt3o12"
					+ System.getProperty("fiel.separator") + "bruchrechner"
					+ System.getProperty("file.separator") + "logs");
		}

		if (!logPath.exists())
			logPath.mkdirs();

		File logFile = null;
		File logErrFile = null;
		try {
			logFile = new File(logPath.getAbsoluteFile()
					+ System.getProperty("file.separator") + "Log_"
					+ getTime().replaceAll("[.:]", "_") + ".log");

			logErrFile = new File(logPath.getAbsoluteFile()
					+ System.getProperty("file.separator") + "Log_"
					+ getTime().replaceAll("[.:]", "_") + ".err.log");

			logFile.createNewFile();
			logErrFile.createNewFile();

			clearOldLogs();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			logOutput = new FileWriter(logFile);
			logErrOutput = new FileWriter(logErrFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		add("-------------------------------------------", false);
		add("Logger wird von " + System.getProperty("user.name")
				+ " gestartet.");
		add("Bruchrechner");

		addErr("-------------------------------------------------------",
				false, false);
		addErr("Error Logger wird von " + System.getProperty("user.name")
				+ " gestartet.", false);
		addErr("Bruchrechner", false);

		loggerInstanc = this;
	}

	public void add() {
		add("", false);
	}

	/**
	 * F&uuml;gt einen neuen Log Eintrag hinzu inklusive Datum und Uhrzeit.
	 * 
	 * @param text
	 *            Der Text des Logeintrages.
	 */
	public void add(Object text) {
		add(text, true);
	}

	/**
	 * F&uuml;gt einen neuen Log Eintrag hinzu.
	 * 
	 * @param text
	 *            Der Text des Log
	 * @param printDate
	 *            false, damit keine Datum mit Uhrzeit und Datum angezigt wird.
	 */
	public void add(Object text, boolean printDate) {
		if (printDate)
			text = getTime() + ": " + text;

		writeText(text.toString(), logOutput, System.out);
	}

	/**
	 * F&uuml;gt einen neuen Error-Log Eintrag hinzu inklusive Datum und
	 * Uhrzeit.
	 * 
	 * @param text
	 *            Text des Error-Logs.
	 */
	public void addErr(Object text) {
		addErr(text, true);
	}

	/**
	 * F&uuml;gt einen neuen Error-Log Eintrag hinzu inklusive Datum und
	 * Uhrzeit.<br>
	 * Hier l&auml;sst sich festlegen, ob ein nach dem Datum "error" kommen
	 * soll. Außerdem wirkt es sich auf die Output stream aus.
	 * 
	 * @param text
	 *            Text des Error-Logs.
	 * @param printError
	 *            Soll vor hinter dem Datum Error geschrieben werden?
	 */
	public void addErr(Object text, boolean printError) {
		addErr(text, printError, true);
	}

	/**
	 * F&uuml;gt einen neuen Error-Log Eintrag hinzu.
	 * 
	 * @param text
	 *            Text des Error-Logs.
	 * @param printError
	 *            Soll vor hinter dem Datum Error geschrieben werden?
	 * @param printDate
	 *            false, damit keine Datum mit Uhrzeit und Datum angezigt wird.
	 */
	public void addErr(Object text, boolean printError, boolean printDate) {
		if (printDate)
			if (printError)
				text = getTime() + " ERROR: " + text;
			else
				text = getTime() + ": " + text;

		String absolutText = text.toString();

		if (printError)
			writeText(absolutText, logErrOutput, System.err);
		else
			writeText(absolutText, logErrOutput);
	}

	/**
	 * Schriebt einen Text in ein FileWriter.
	 * 
	 * @param text
	 *            Der text der im outputWriter geschrieben wird.
	 * @param outputWriter
	 *            Das FileWriter Object indem der Text geschrieben wird.
	 */
	private void writeText(String text, FileWriter outputWriter) {
		try {
			outputWriter.write(text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Schriebt einen Text in ein FileWriter und schreibt es ebenfalls in eine
	 * printStream.
	 * 
	 * @param text
	 *            Der text der im outputWriter und printStream geschrieben wird.
	 * @param outputWriter
	 *            FileWriter Object indem der Text geschrieben wird.
	 * @param printStraem
	 *            Die printStream in der der Text geschrieben wird.
	 */
	private void writeText(String text, FileWriter outputWriter,
			PrintStream printStraem) {
		printStraem.println(text);
		writeText(text, outputWriter);
	}

	/**
	 * Schreibt das Ende der Logfiles und schließt sie.<br>
	 * <b>Muss aufgerufen werden, damit der Log sicher geschlossen wird.</b>
	 */
	public void close() {
		add();
		add("Logger wird geschlossen.");

		addErr("", false, false);
		addErr("Error Logger wird geschlossen.", false);

		try {
			logOutput.close();
			logErrOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		loggerInstanc = null;
	}

	/**
	 * L&ouml;scht alte log eintr&auml;ge.
	 */
	private void clearOldLogs() {
		// Clear logs:
		ArrayList<File> oldLogs = new ArrayList<File>();

		for (File logFile : logPath.listFiles())
			if (logFile.isFile() && logFile.getName().endsWith(".log")
					&& !logFile.getName().endsWith(".err.log"))
				oldLogs.add(logFile);

		Collections.sort(oldLogs);
		for (int i = 0; i < oldLogs.size() - keepMaxLogs; i++) {
			oldLogs.get(i).delete();
		}

		// Clear error logs:
		ArrayList<File> oldErrorLogs = new ArrayList<File>();

		for (File errorLogFile : logPath.listFiles())
			if (errorLogFile.isFile()
					&& errorLogFile.getName().endsWith(".err.log"))
				oldErrorLogs.add(errorLogFile);

		Collections.sort(oldErrorLogs);
		for (int i = 0; i < oldErrorLogs.size() - 3; i++)
			oldErrorLogs.get(i).delete();
	}

	/**
	 * gibt die Zeit so wieder, wie sie gelogt werden soll.
	 */
	private String getTime() {
		java.util.Date date = new java.util.Date();
		return String.format("%td.%<tm.%<tY %<tH:%<tM:%<tS", date);
	}

	/**
	 * Gibt die Letze verwendete Logger Instance zurück.
	 * 
	 * @return Gibt die Letze verwendete Logger Instance zurück.
	 */
	public static Logger getLogger() {
		if (loggerInstanc == null)
			new Logger();

		return loggerInstanc;
	}
}
