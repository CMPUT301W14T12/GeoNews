package ca.ualberta.cs.models;

// Credit: https://github.com/rayzhangcl/ESDemo

import java.util.ArrayList;
import java.util.Collection;

/**
 * Stores information from a ES search response
 * @author wyatt
 *
 * @param <T>
 */
public class ElasticSearchSearchResponse<T> {
	int took;
	boolean timed_out;
	transient Object _shards;
	Hits<T> hits;
	boolean exists;

	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits.getHits();
	}

	public Collection<T> getSources() {
		Collection<T> out = new ArrayList<T>();
		for (ElasticSearchResponse<T> essrt : getHits()) {
			out.add(essrt.getSource());
		}
		return out;
	}

	@Override
	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists
				+ "," + hits);
	}
}
