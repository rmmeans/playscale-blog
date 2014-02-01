package controllers;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class HealthCheckController extends Controller {

	public static Result getHealthCheckStatus() {

		return ok(getMemoryUsage());
	}

	private static ObjectNode getMemoryUsage() {
		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = memBean.getHeapMemoryUsage();
		MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();

		ObjectNode node = Json.newObject();
		node.put("heap-used-mb", heap.getUsed() / 1024 / 1024);
		node.put("heap-committed-mb", heap.getCommitted() / 1024 / 1024);
		node.put("heap-max-mb", heap.getMax() / 1024 / 1024);
		node.put("nonHeap-used-mb", nonHeap.getUsed() / 1024 / 1024);
		node.put("nonHeap-committed-mb", nonHeap.getCommitted() / 1024 / 1024);
		node.put("nonHeap-max-mb", nonHeap.getMax() / 1024 / 1024);

		return node;
	}
}
