import React,{useState,useEffect} from "react";
import axios from "axios";
import DropZone from "./DropZone";
const UserProfiles = () => {
    const [userProfiles,setUserProfiles] = useState([]);
    const fetchUserProfiles = () => {
        axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
            console.log(res);
            setUserProfiles(res.data);
        })
    }
    useEffect(() => {
        fetchUserProfiles();
    },[]);

    return userProfiles.map((userProfile,index) => {
        
        return (
            <div key={index}>
                {
                    /* profile to be done */
                }
                <br/>
                <br/>
                <h1>{userProfile.username}</h1>
                <p>{userProfile.userProfileId}</p>
                <DropZone {...userProfile}/>
                <br/>
            </div>
        )
    })
}

export default UserProfiles;