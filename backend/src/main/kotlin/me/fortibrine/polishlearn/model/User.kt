package me.fortibrine.polishlearn.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
class User (
    @field:Id
    var id: String? = null,
    private var username: String = "",
    private var password: String = "",
    var email: String = ""
): UserDetails {
    override fun getAuthorities() = listOf<GrantedAuthority>()
    override fun getPassword() = password
    override fun getUsername() = username
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

    fun setPassword(password: String) {
        this.password = password
    }

    fun setUsername(username: String) {
        this.username = username
    }

}
