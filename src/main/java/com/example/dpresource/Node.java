
package com.example.dpresource;

import java.util.List;

public class Node {

    private String nodeId;
    private int maxTables;
    private List<TerminationPoint> terminationPoint = null;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getMaxTables() {
        return maxTables;
    }

    public void setMaxTables(int maxTables) {
        this.maxTables = maxTables;
    }

    public List<TerminationPoint> getTerminationPoint() {
        return terminationPoint;
    }

    public void setTerminationPoint(List<TerminationPoint> terminationPoint) {
        this.terminationPoint = terminationPoint;
    }

	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", maxTables=" + maxTables + ", terminationPoint=" + terminationPoint + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxTables;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		result = prime * result + ((terminationPoint == null) ? 0 : terminationPoint.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (maxTables != other.maxTables)
			return false;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		if (terminationPoint == null) {
			if (other.terminationPoint != null)
				return false;
		} else if (!terminationPoint.equals(other.terminationPoint))
			return false;
		return true;
	}

}
