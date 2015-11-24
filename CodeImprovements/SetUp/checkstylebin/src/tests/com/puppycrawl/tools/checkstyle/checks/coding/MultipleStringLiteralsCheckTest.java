package com.puppycrawl.tools.checkstyle.checks.coding;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import java.io.File;
import org.junit.Test;

public class MultipleStringLiteralsCheckTest extends BaseCheckTestSupport
{
    @Test
    public void testIt() throws Exception
    {
        DefaultConfiguration checkConfig =
            createCheckConfig(MultipleStringLiteralsCheck.class);
        checkConfig.addAttribute("allowedDuplicates", "2");
        checkConfig.addAttribute("ignoreStringsRegexp", "");

        final String[] expected = {
            "5:16: The String \"StringContents\" appears 3 times in the file.",
            "8:17: The String \"\" appears 4 times in the file.",
            "10:23: The String \", \" appears 3 times in the file.",
            };

        verify(checkConfig,
               getPath("coding" + File.separator + "InputMultipleStringLiterals.java"),
               expected);
    }

    @Test
    public void testItIgnoreEmpty() throws Exception
    {
        DefaultConfiguration checkConfig =
            createCheckConfig(MultipleStringLiteralsCheck.class);
        checkConfig.addAttribute("allowedDuplicates", "2");

        final String[] expected = {
            "5:16: The String \"StringContents\" appears 3 times in the file.",
            "10:23: The String \", \" appears 3 times in the file.",
        };

        verify(checkConfig,
               getPath("coding" + File.separator + "InputMultipleStringLiterals.java"),
               expected);
    }

    @Test
    public void testItIgnoreEmptyAndComaSpace() throws Exception
    {
        DefaultConfiguration checkConfig =
            createCheckConfig(MultipleStringLiteralsCheck.class);
        checkConfig.addAttribute("allowedDuplicates", "2");
        checkConfig.addAttribute("ignoreStringsRegexp", "^((\"\")|(\", \"))$");

        final String[] expected = {
            "5:16: The String \"StringContents\" appears 3 times in the file.",
        };

        verify(checkConfig,
               getPath("coding" + File.separator + "InputMultipleStringLiterals.java"),
               expected);
    }

    @Test
    public void testItWithoutIgnoringAnnotations() throws Exception
    {
        DefaultConfiguration checkConfig =
            createCheckConfig(MultipleStringLiteralsCheck.class);
        checkConfig.addAttribute("allowedDuplicates", "3");
        checkConfig.addAttribute("ignoreOccurrenceContext", "");

        final String[] expected = {
            "19:23: The String \"unchecked\" appears 4 times in the file.",
        };

        verify(checkConfig,
               getPath("coding" + File.separator + "InputMultipleStringLiterals.java"),
               expected);
    }

}
