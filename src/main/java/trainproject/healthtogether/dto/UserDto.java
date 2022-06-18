package trainproject.healthtogether.dto;

import trainproject.healthtogether.domain.manytomany.UserGroup;
import trainproject.healthtogether.domain.user.Friend;

import java.util.List;

public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String picture;

    private String nickname;

    private Friend friendList;

    private List<ExerciseGroupDto> exerciseGroupDtoList;

    protected UserDto() {

    }

    /*
    * Attend 데이터를 넘겨줄 때 사용할 최소한의 User 정보
     */
    public UserDto(Long id, String name, String email, String picture, String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
    }

    /*
    * User 정보 데이터를 넘겨줄 때
     */
    public UserDto(Long id, String name, String email, String picture, String nickname, Friend friendList, List<UserGroup> exerciseGroupList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
        this.friendList = friendList;

        for (UserGroup userGroup : exerciseGroupList) {
            this.exerciseGroupDtoList.add(new ExerciseGroupDto(userGroup.getExerciseGroup().getId(), userGroup.getExerciseGroup().getExerciseGroupName(),
                    userGroup.getExerciseGroup().getIntro(), userGroup.getExerciseGroup().getRoutineTime(), userGroup.getExerciseGroup().getStartDate(), userGroup.getExerciseGroup().getTargetDay(),
                    userGroup.getExerciseGroup().getUserGroupList()));
        }
    }
}