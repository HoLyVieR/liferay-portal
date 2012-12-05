/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;

/**
 * @author Bruno Basto
 */
public class DocumentLibraryFieldRenderer extends BaseFieldRenderer {

	@Override
	protected String doRender(Field field, Locale locale) throws Exception {
		List<Serializable> values = field.getValues();

		StringBundler sb = new StringBundler(values.size() * 2);

		for (int i = 0; i < values.size(); i++) {
			String value = String.valueOf(values.get(i));

			if (Validator.isNull(value)) {
				continue;
			}

			sb.append(handleJSON(value, locale));

			if ((i + 1) < values.size()) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	@Override
	protected String doRender(Field field, Locale locale, int valueIndex) {
		String value = String.valueOf(field.getValue(valueIndex));

		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		return handleJSON(value, locale);
	}

	protected String handleJSON(String json, Locale locale) {
		JSONObject fieldValueJSONObject = null;

		try {
			fieldValueJSONObject = JSONFactoryUtil.createJSONObject(json);
		}
		catch (JSONException jsone) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse JSON", jsone);
			}

			return StringPool.BLANK;
		}

		long fileEntryGroupId = fieldValueJSONObject.getLong("groupId");
		String fileEntryUUID = fieldValueJSONObject.getString("uuid");

		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntryByUuidAndGroupId(
				fileEntryUUID, fileEntryGroupId);

			return fileEntry.getTitle();
		}
		catch (Exception e) {
			if (e instanceof NoSuchFileEntryException ||
				e instanceof PrincipalException) {

				return LanguageUtil.format(
					locale, "is-temporarily-unavailable", "content");
			}
		}

		return StringPool.BLANK;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DocumentLibraryFieldRenderer.class);

}