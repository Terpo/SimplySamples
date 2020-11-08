package org.terpo.simplysamples;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RegistryNames {

	public static final String SAMPLE_SUFFIX = "_sample";

	private static Map<String, Integer> getOreMap() {
		Map<String, Integer> oreMap = new HashMap<>();
		oreMap.put("coal", Integer.valueOf(0x343434));
		oreMap.put("iron", Integer.valueOf(0x6b2f1d));
		oreMap.put("diamond", Integer.valueOf(0x65f5e3));
		oreMap.put("emerald", Integer.valueOf(0x17dd62));
		oreMap.put("gold", Integer.valueOf(0xfcee4b));
		oreMap.put("aluminum", Integer.valueOf(0x856250));
		oreMap.put("lead", Integer.valueOf(0x39333c));
		oreMap.put("silver", Integer.valueOf(0xa9b5bc));
		oreMap.put("copper", Integer.valueOf(0x4f7862));
		oreMap.put("tin", Integer.valueOf(0xdbdbdb));
		oreMap.put("nickel", Integer.valueOf(0x98a098));
		oreMap.put("uranium", Integer.valueOf(0x57744b));
		oreMap.put("osmium", Integer.valueOf(0x8dafba));
		return oreMap;
	}

	// BLOCKS && ITEM_BLOCKS
	public static final Map<String, Integer> SAMPLES = getOreMap().entrySet().stream()
			.collect(Collectors.toMap(e -> e.getKey() + SAMPLE_SUFFIX, Entry::getValue));

	private RegistryNames() {
		// hidden
	}

}