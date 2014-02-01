
import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._
import com.excilys.ebi.gatling.http.Headers.Names._
import akka.util.duration._
import bootstrap._
import assertions._

class PlayScaleLoadTest extends Simulation {

	val httpConf = httpConfig
			.baseURL("http://192.168.33.10")
			.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			.acceptEncodingHeader("gzip, deflate")
			.acceptLanguageHeader("en-us,en;q=0.5")
			.connection("keep-alive")
			.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:26.0) Gecko/20100101 Firefox/26.0")

	val scn = scenario("Scenario Name")
		.exec(http("request_1")
					.get("/sampler/12345")
			)
		.pause(250 milliseconds)
		.exec(http("request_2")
					.get("/sampler/23456")
			)
		.pause(250 milliseconds)
		.exec(http("request_3")
					.get("/sampler/34567")
			)
		.pause(250 milliseconds)
		.exec(http("request_4")
					.get("/sampler/45678")
			)
		.pause(250 milliseconds)
		.exec(http("request_5")
					.get("/sampler/56789")
			)
		.pause(250 milliseconds)
		.exec(http("request_6")
					.get("/sampler/678910")
			)
	setUp(scn.users(2500).ramp(60).protocolConfig(httpConf))
}