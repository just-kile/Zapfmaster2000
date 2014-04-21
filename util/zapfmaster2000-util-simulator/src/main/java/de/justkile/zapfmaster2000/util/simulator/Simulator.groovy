package de.justkile.zapfmaster2000.util.simulator

import java.text.SimpleDateFormat

import org.apache.http.HttpEntity
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils

class Simulator {


	//	private static final String url = "http://localhost:8080/zapfmaster2000-service/rest";
	private static final String url = "http://zapfmaster2000.dnsdynamic.com:45580/zapfmaster2000-service/rest";
	private HttpClient httpclient = new DefaultHttpClient()

	private long lastCommandTime = -1
	private long lastRealTime = -1

	static main(args) {
		while (true) {
			if (args.length != 1) {
				println "One argument needed: Input file."
			} else {
				def inputFile = args[0]
				new Simulator().runSimulation(inputFile)
				println "Done."
			}
		}
	}

	void runSimulation(String inputFile) {
		def commands = parseScript(inputFile)
		printStatistics(commands)
		executeCommands(commands)
	}

	private List<Command> parseScript(String inputFile) {
		def regex = /(\d+) (\w+)(.*)/
		def result = new ArrayList()
		new File(inputFile).eachLine { line ->
			def matcher = line =~ regex
			if (matcher.matches()) {
				def time =  matcher[0][1].toLong()
				def command =  matcher[0][2]
				def arguments =  matcher[0][3].split(" ").collect{it.trim()}.grep{it != ""}
				result.add(new Command(time: time, command: command, arguments: arguments))
			} else {
				println "Skipping invalid line in script: '$line'."
			}
		}
		result
	}

	private void printStatistics(List<Command> commands) {
		def duration = commands[commands.size - 1].time - commands[0].time
		def formatter = new SimpleDateFormat("HH:mm:ss.SSS")
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"))
		def formattedTime = formatter.format(new Date(duration))

		println "************************************"
		println "* Statistics"
		println "* # commands: $commands.size"
		println "* duration: $formattedTime"
		println "************************************"
	}

	private void executeCommands(List<Command> commands) {
		commands.each { command ->
			waitUntil(command.time)
			def capitalizedCommandName = command.command.toLowerCase().capitalize()
			this."execute$capitalizedCommandName"(command.arguments)
		}
	}

	private void executeTicks(List<String> arguments) {
		def numTicks = arguments[0].toInteger()
		def boxName = arguments[2]
		println "processing ticks $numTicks at $boxName"
		performTickRequest(numTicks, boxName)
	}

	private void executeRfid(List<String> arguments) {
		def rfidTag = arguments[0].toLong()
		def boxName = arguments[2]
		println "processing rfid tag $rfidTag at $boxName"
		performRfidRequest(rfidTag, boxName)
	}

	private void waitUntil(long time) {
		if (lastCommandTime != -1) {
			// -1 if it's the first command
			def diff = time - lastCommandTime;
			def realDiff = System.currentTimeMillis() - lastRealTime
			def timeToWait = diff - realDiff
			if (timeToWait < 0) {
				println "I am too late: ${-timeToWait}"
			}

			while (realDiff < diff) {
				Thread.sleep(timeToWait)
				realDiff = System.currentTimeMillis() - lastRealTime
				timeToWait = diff - realDiff
			}
		}

		lastCommandTime = time;
		lastRealTime = System.currentTimeMillis()
	}

	private void performRfidRequest(long rfid, String box)	{
		String url =  "$url/box/login?rfid=$rfid&passphrase=$box"
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);

		HttpEntity entitiy = response.getEntity()
		println transformToString(entitiy.content)
		EntityUtils.consume(entitiy);
		httpGet.releaseConnection();
	}

	private void performTickRequest(int numTicks, String box) {
		String url =  "$url/box/draw?ticks=$numTicks&passphrase=$box"
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);

		HttpEntity entitiy = response.getEntity()
		println transformToString(entitiy.content)
		EntityUtils.consume(entitiy);
		httpGet.releaseConnection();
	}

	public  String  transformToString(InputStream  inputStream) {
		StringBuffer  out  =  new  StringBuffer();
		InputStreamReader inread = new InputStreamReader(inputStream);

		char[] b  =  new char[4096];
		for (int  n;  (n  =  inread.read(b))  !=  -1;)  {
			out.append(new  String(b,  0,  n));
		}

		return out.toString();
	}
}
