package com.puppycrawl.tools.checkstyle.checks.coding;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import java.io.File;
import org.junit.Test;

public class RequireThisCheckTest extends BaseCheckTestSupport
{
    @Test
    public void testIt() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(RequireThisCheck.class);
        final String[] expected = {
            "6:9: Reference to instance variable 'i' needs \"this.\".",
            "12:9: Method call to 'method1' needs \"this.\".",
            "26:9: Reference to instance variable 'i' needs \"this.\".",
            "51:9: Reference to instance variable 'z' needs \"this.\".",
//              "13:9: Unable find where 'j' is declared.",
        };
        verify(checkConfig,
               getPath("coding" + File.separator + "InputRequireThis.java"),
               expected);
    }

    @Test
    public void testMethodsOnly() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(RequireThisCheck.class);
        checkConfig.addAttribute("checkFields", "false");
        final String[] expected = {
            "12:9: Method call to 'method1' needs \"this.\".",
        };
        verify(checkConfig,
               getPath("coding" + File.separator + "InputRequireThis.java"),
               expected);
    }

    @Test
    public void testFieldsOnly() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(RequireThisCheck.class);
        checkConfig.addAttribute("checkMethods", "false");
        final String[] expected = {
            "6:9: Reference to instance variable 'i' needs \"this.\".",
            "26:9: Reference to instance variable 'i' needs \"this.\".",
            "51:9: Reference to instance variable 'z' needs \"this.\".",
//              "13:9: Unable find where 'j' is declared.",
        };
        verify(checkConfig,
               getPath("coding" + File.separator + "InputRequireThis.java"),
               expected);
    }

    @Test
    public void testGenerics() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(RequireThisCheck.class);
        final String[] expected = {};
        verify(checkConfig, getPath("Input15Extensions.java"), expected);
    }
}
