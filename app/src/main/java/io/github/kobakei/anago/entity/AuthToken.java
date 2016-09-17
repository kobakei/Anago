package io.github.kobakei.anago.entity;

import java.util.List;

/**
 * Created by keisuke on 2016/09/18.
 */

public class AuthToken {
    public long id;
    public String token;
    public String hashed_token;
    public List<String> scopes;
    public String fingerprint;
}
