
package com.example.dpresource;

import org.neo4j.cypher.internal.compiler.v2_2.docgen.plannerDocGen.predicateConverter;

public class TerminationPoint {

    private String tpId;
    private int maximumSpeed;
    private int currentSpeed;

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }


	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	@Override
	public String toString() {
		return "TerminationPoint [tpId=" + tpId + ", maximumSpeed=" + maximumSpeed + ", currentSpeed=" + currentSpeed
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentSpeed;
		result = prime * result + maximumSpeed;
		result = prime * result + ((tpId == null) ? 0 : tpId.hashCode());
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
		TerminationPoint other = (TerminationPoint) obj;
		if (currentSpeed != other.currentSpeed)
			return false;
		if (maximumSpeed != other.maximumSpeed)
			return false;
		if (tpId == null) {
			if (other.tpId != null)
				return false;
		} else if (!tpId.equals(other.tpId))
			return false;
		return true;
	}

}
