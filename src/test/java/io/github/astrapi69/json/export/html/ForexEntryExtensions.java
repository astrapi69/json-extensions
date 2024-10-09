/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.json.export.html;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForexEntryExtensions
{

	public static Map<String, ArrayList<ForexEntry>> readForexEntries(URL url) throws IOException
	{
		Document doc = Jsoup.parse(url, 5000);
		Map<String, ArrayList<ForexEntry>> forexEntries = new HashMap<>();
		ArrayList<ForexEntry> entries = new ArrayList<>();
		ArrayList<ForexEntry> subEntries = new ArrayList<>();
		// select the first table
		Element table = doc.select("table").get(0);
		// select all rows
		Elements rows = table.select("tr");

		System.out.println("rows.size:" + rows.size());
		String title;

		for (Element row : rows)
		{ // first row is the col names so skip it.
			if (row.hasClass("displayNone"))
			{
				continue;
			}
			Elements cols = row.select("td");
			System.out.println("cols.size:" + cols.size());
			System.out.println(cols.html());
			if (cols.size() == 1)
			{
				Element element = cols.get(0);
				title = element.text();
				subEntries = new ArrayList<>();
				forexEntries.put(title, subEntries);
				System.out.println(element.html());
			}

			if (cols.size() == 8)
			{
				Element sentiment = cols.get(2);
				Elements children = sentiment.children();
				Elements grayFullBullishIcon = children.select("i.grayFullBullishIcon");

				Elements diamond = cols.get(7).children();
				int diamondSize = diamond.size();
				ForexEntry forexEntry = ForexEntry.builder().time(cols.get(0).text())
					.cur(cols.get(1).text()).impl(grayFullBullishIcon.size() + "")
					.event(cols.get(3).text()).actual(cols.get(4).text())
					.forecast(cols.get(5).text()).previous(cols.get(6).text())
					.diamond(diamondSize + "").build();
				subEntries.add(forexEntry);
				entries.add(forexEntry);
			}
		}

		forexEntries.put("All forex entries", entries);
		return forexEntries;
	}

}
