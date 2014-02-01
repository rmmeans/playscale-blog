import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;

import org.junit.Test;

import play.mvc.Content;

public class TemplateTest {

	@Test
	public void renderTemplate() {
		Content html = views.html.main.render("123", "yellow");
		assertThat(contentType(html)).isEqualTo("text/html");
		assertThat(contentAsString(html)).contains(
				"Response came from the yellow server");
		assertThat(html.body().contains("123"));
	}

}
