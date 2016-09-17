package io.github.kobakei.anago.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

import java.util.List;

/**
 * Created by keisuke on 2016/09/18.
 */
@Table
public class AuthToken {
    @PrimaryKey
    public final long id;
    @Column
    public final String token;
    @Column
    public final String hashed_token;
    @Column
    public final List<String> scopes;
    @Column
    public final String fingerprint;

    @Setter
    AuthToken(long id, String token, String hashed_token, List<String> scopes, String fingerprint) {
        this.id = id;
        this.token = token;
        this.hashed_token = hashed_token;
        this.scopes = scopes;
        this.fingerprint = fingerprint;
    }
}
