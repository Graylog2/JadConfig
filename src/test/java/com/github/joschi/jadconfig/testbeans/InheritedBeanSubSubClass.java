package com.github.joschi.jadconfig.testbeans;

import com.github.joschi.jadconfig.Parameter;

import java.net.URI;

/**
 * @author Dennis Oelkers <dennis@torch.sh>
 */
public class InheritedBeanSubSubClass extends InheritedBeanSubClass {
    @Parameter("test.uri")
    private URI myUri;

    public URI getMyUri() {
        return myUri;
    }

    public void setMyUri(URI myUri) {
        this.myUri = myUri;
    }
}
