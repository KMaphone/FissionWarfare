package tm.fissionwarfare.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;

public class SecurityProfile {

	private String teamName;
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(Team team) {
		teamName = team.getRegisteredName();
	}
	
	public void setTeamNameFromPlayer(EntityPlayer player) {
		teamName = player.getTeam().getRegisteredName();
	}
	
	public boolean isSameTeam(EntityPlayer player) {
		
		Team team = player.worldObj.getScoreboard().getTeam(teamName);
		
		return team.isSameTeam(player.getTeam());
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		teamName = nbt.getString("teamName");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setString("teamName", teamName);
	}
}
