package org.terrakube.api.rs.hooks.organization;

import com.yahoo.elide.annotation.LifeCycleHookBinding;
import com.yahoo.elide.core.lifecycle.LifeCycleHook;
import com.yahoo.elide.core.security.ChangeSpec;
import com.yahoo.elide.core.security.RequestScope;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.terrakube.api.plugin.manage.OrganizationManageService;
import org.terrakube.api.rs.Organization;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class OrganizationManageHook implements LifeCycleHook<Organization> {

    OrganizationManageService organizationManageService;
    @Override
    public void execute(LifeCycleHookBinding.Operation operation, LifeCycleHookBinding.TransactionPhase transactionPhase, Organization organization, RequestScope requestScope, Optional<ChangeSpec> optional) {
        log.info("OrganizationManageHook {}", organization.getId());
            switch (operation) {
                case CREATE:
                    organizationManageService.postCreationSetup(organization);
                    break;
                default:
                    log.info("Not supported");
                    break;
            }

    }
}
