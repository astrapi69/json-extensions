package io.github.astrapi69.json.export.html;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForexEntry
{

	int listIndex;

	String time;

	String cur;

	String impl;

	String event;

	String actual;

	String forecast;

	String previous;

	String diamond;

}
