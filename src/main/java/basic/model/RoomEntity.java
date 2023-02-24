package basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_room", schema = "sych")
public class RoomEntity {
    private Long roomId;

    @Id
    @Column(name = "room_id")
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}