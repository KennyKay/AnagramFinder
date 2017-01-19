import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PerformanceMeter {

	private List<Long> times = new ArrayList<>();

	private boolean isRunning;

	private long start;
	private long end;


	public boolean isRunning() {

		return this.isRunning;
	}


	public List<Long> top(int number) {

		List<Long> copy = new ArrayList<>(this.times);
		copy.sort(Long::compareTo);

		return copy.subList(0, number);
	}


	public Long latest() {

		return this.times.get(this.times.size() - 1);
	}


	public void start() {

		if (isRunning) return;

		this.isRunning = true;
		this.start = System.nanoTime();
	}


	public void stop() {

		if (!isRunning) return;

		this.isRunning = false;
		this.end = System.nanoTime();

		this.times.add(this.end - this.start);
	}
}
