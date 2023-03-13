import axios from "axios";
import React , {useCallback} from "react";
import { useDropzone } from "react-dropzone";

const DropZone = ({userProfileId}) => {

    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
        const formData = new FormData();
        formData.append("file",file);
        
        axios.post(
            `http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`,
            formData,
            {
                headers:{
                    "Content-Type": "multipart/form-data"
                }
            }

        )
    },[]);
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});
    return (
        <div {...getRootProps()}>
            <input{...getInputProps()} />
            {
               isDragActive ? (<p>Drop Files Here</p>) : (<p>Drag 'n' Drop profile pic Here</p>)
            }
        </div>
    )

}

export default DropZone;