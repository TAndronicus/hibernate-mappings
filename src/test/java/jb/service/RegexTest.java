package jb.service;

import org.junit.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexTest {

	@Test
	public void should() {
		VerbalExpression verbalExpression = VerbalExpression.regex()
			.startOfLine()
			.then("s").atLeast(2)
			.oneOf("ab", "bb", "cc")
			.capture("a").word().endCapture()
			.then("a").count(3)
			.build();
		assertTrue(verbalExpression.test("sssccdeaaa"));
		assertEquals("de", verbalExpression.getText("sssccdeaaa", "a"));
		assertTrue(verbalExpression.test("sssssssbbqaaa"));
		assertFalse(verbalExpression.test("sabaaa"));
		assertFalse(verbalExpression.test("ssaaaaa"));
		assertFalse(verbalExpression.test("ssabaa"));
	}

}
