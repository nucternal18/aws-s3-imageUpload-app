import { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import axios from 'axios';

const MyDropzone = ({ userProfileId }) => {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0];
    console.log(file);
    const formData = new FormData();
    formData.append('file', file);
    axios
      .post(`/api/v1/user-profile/${userProfileId}/image/upload`, formData, {
        headers: {
          'content-type': 'multipart/form-data',
        },
      })
      .then(() => console.log('File uploaded successfully'))
      .catch((err) => console.log('error: ' + err));
  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the Profile image here ...</p>
      ) : (
        <p>
          Drag 'n' drop Profile image here, or click to select profile image
        </p>
      )}
    </div>
  );
};

export default MyDropzone;
