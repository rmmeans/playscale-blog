package controllers;

import play.Configuration;
import play.cache.Cache;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Akka;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.ExecutionContext;

public class Application extends Controller {

	private static final String CACHE_PREFIX = "FrontEnd-DocItem::";
	private static final String BACKEND_URL = Configuration.root().getString(
			"backend.server");
	private static final String SERVER_COLOR = Configuration.root().getString(
			"server.color", "brown");

	public static Promise<Result> index(final Long id) {
		final Promise<Result> resultPromise;
		final String cacheKey = CACHE_PREFIX + id;

		ExecutionContext cacheExecutionContext = Akka.system().dispatchers()
				.lookup("contexts.distributed-cache-context");

		Promise<Object> promiseOfCache = Promise.promise(
				new Function0<Object>() {
					public Object apply() {
						return Cache.get(cacheKey);
					}
				}, cacheExecutionContext);

		resultPromise = promiseOfCache
				.flatMap(new Function<Object, Promise<Result>>() {
					public Promise<Result> apply(Object obj) {
						if (obj == null) {
							return WS.url(BACKEND_URL + id).get()
									.map(new Function<WS.Response, Result>() {
										public Result apply(WS.Response response) {
											String txtBody = response.getBody();
											Cache.set(cacheKey, txtBody);
											return ok(views.html.main.apply(
													txtBody, SERVER_COLOR));
										}
									});
						} else {
							return Promise.<Result> pure(ok(views.html.main
									.apply((String) obj, SERVER_COLOR)));
						}
					}
				});

		return resultPromise;
	}
}
