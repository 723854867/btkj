package org.btkj.common.web.session;

public interface ISession {
	
	void write(String reply);

	void write(byte[] reply);
}
