package message;

import jade.lang.acl.ACLMessage;

public class JessACLMessage extends ACLMessage
{
	protected String content;
	
	public JessACLMessage( int perf )
	{
		super( perf );
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
	
	

}
