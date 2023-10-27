package de.helpernet.pokopr.integration.teamdrive.client.dto;

import de.helpernet.pokopr.integration.teamdrive.client.response.GetSpacesResponse;

public class SpaceDto {

    private String id;

    private String name;

    private SpaceStatus status;

    public static SpaceDto from(GetSpacesResponse spaceModel) {
        SpaceDto spaceDto = new SpaceDto();
        spaceDto.setId(spaceModel.getId());
        spaceDto.setName(spaceModel.getName());
        spaceDto.setStatus(SpaceStatus.fromExternalValue(spaceModel.getStatus()));
        return spaceDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpaceStatus getStatus() {
        return status;
    }

    public void setStatus(SpaceStatus status) {
        this.status = status;
    }
}
