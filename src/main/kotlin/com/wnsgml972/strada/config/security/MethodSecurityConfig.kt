package com.wnsgml972.strada.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import java.util.ArrayList

@Profile("!default")
@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
class MethodSecurityConfig : GlobalMethodSecurityConfiguration() {

    override fun accessDecisionManager(): AccessDecisionManager {
        val decisionVoters = ArrayList<AccessDecisionVoter<*>?>()

        decisionVoters.add(RoleVoter().apply {
            this.rolePrefix = ""
        })
        decisionVoters.add(AuthenticatedVoter())

        return AffirmativeBased(decisionVoters)
    }
}
