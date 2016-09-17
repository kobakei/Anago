package io.github.kobakei.anago.net.body;

import java.util.List;

/**
 * Created by keisuke on 2016/09/18.
 */

public class AuthorizationBody {
    public String client_secret;
    public List<String> scopes;
    public String note;
    public String note_url;
}
