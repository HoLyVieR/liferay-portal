/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.staging.configuration.web.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.staging.configuration.web.portlet.constants.StagingConfigurationPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Levente Hudák
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_PUBLISHING_TOOLS,
		"service.ranking:Integer=100"
	},
	service = PanelApp.class
)
public class StagingConfigurationPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return StagingConfigurationPortletKeys.STAGING_CONFIGURATION;
	}

	@Override
	public boolean hasAccessPermission(
			PermissionChecker permissionChecker, Group group)
		throws PortalException {

		if (group.isLayoutPrototype() || group.isLayoutSetPrototype()) {
			return false;
		}

		return true;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + StagingConfigurationPortletKeys.STAGING_CONFIGURATION + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}