package org.btkj.bihu.vehicle;

import org.btkj.pojo.enums.IDType;

public class BiHuUtil {

	public static final IDType idType(int idType) {
		switch (idType) {
		case 1:
			return IDType.IDENTITY;
		case 2:
			return IDType.ORGANIZATION_LICENSE;
		case 3:
			return IDType.PASSPORT;
		case 4:
			return IDType.MILITARY_CARD;
		case 5:
			return IDType.RE_ENTRY_PERMIT;
		case 6:
		case 8:
			return IDType.OTHER;
		case 7:
			return IDType.EEP_HK_MACAU;
		case 9:
			return IDType.BUSINESS_LICENSE;
		case 10:
			return IDType.TAX_REGISTRATION_CERTIFICATE;
		default:
			throw new IllegalArgumentException("unknown bihu id type " + idType);
		}
	}
}
