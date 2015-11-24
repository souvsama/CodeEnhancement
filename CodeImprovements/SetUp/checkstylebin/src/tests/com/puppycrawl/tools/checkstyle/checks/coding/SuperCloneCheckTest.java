package com.puppycrawl.tools.checkstyle.checks.coding;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import org.junit.Test;

public class SuperCloneCheckTest
    extends BaseCheckTestSupport
{
    @Test
    public void testIt() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(SuperCloneCheck.class);
        final String[] expected = {
            "27:19: Method 'clone' should call 'super.clone'.",
            "35:19: Method 'clone' should call 'super.clone'.",
            "60:23: Method 'clone' should call 'super.clone'.",
        };
        verify(checkConfig, getPath("coding/InputClone.java"), expected);
    }
}
