package web.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import static web.common.Constant.DEFAULT_PARTITION_SIZE;
import web.common.GsonFactory;

/**
 * Represents a group of metadatum. Also provides way to partition the object
 * itself into smaller parts to facilitate paginated access.
 */
public class Metadata implements Iterable<Metadatum> {

	private final List<Metadatum> data = new ArrayList<Metadatum>();

	@Override
	public Iterator<Metadatum> iterator() {
		// sort at time of access
		Collections.sort(data, Metadatum.getDateComparator());
		return data.iterator();
	}

	public void add(Metadatum metadatum) {
		data.add(metadatum);
	}

	public int size() {
		return data.size();
	}

	public List<Metadata> partition(int partitionSize) {
		List<Metadata> partitions = new LinkedList<Metadata>();
		int partitionCount = (data.size() + partitionSize - 1) / partitionSize;
		for (int i = 0; i < partitionCount; i++) {
			int startInc = partitionSize * i;
			int toExc = startInc + partitionSize;
			if (toExc > data.size()) {
				toExc = data.size();
			}
			Metadata partition = new Metadata();
			partition.data.addAll(this.data.subList(startInc, toExc));
			partitions.add(partition);
		}
		return partitions;
	}

	public List<Metadata> partition() {
		return partition(DEFAULT_PARTITION_SIZE);
	}

	public static Metadata read(String inputFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		Gson gson = GsonFactory.createWithUTCdateAdapter();
		Metadata metadata = gson.fromJson(br, Metadata.class);
		br.close();
		return metadata;
	}

	public void write(String outputFile) throws IOException {
		Gson gson = GsonFactory.createWithUTCdateAdapter();
		String json = gson.toJson(this);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		bw.write(json);
		bw.close();
	}

}
