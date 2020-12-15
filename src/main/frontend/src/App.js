import { useEffect, useState } from 'react';
import axios from 'axios';

import MyDropZone from './component/MyDropZone';
import './App.css';

function App() {
  const [userProfiles, setUserProfiles] = useState([]);
  console.log(userProfiles);

  useEffect(() => {
    const fetchUserProfiles = async () => {
      const { data } = await axios.get('/api/v1/user-profile');
      setUserProfiles(data);
    };
    fetchUserProfiles();
  }, []);

  return (
    <main className='App'>
      {userProfiles.map((user) => {
        return (
          <section key={user.userProfileId} className='card'>
            {user.userProfileId ? <img src={`/api/v1/user-profile/${user.userProfileId}/image/download`} alt=""/> : null}
            <div className="container">
              <MyDropZone userProfileId={user.userProfileId} />
            </div>
            <div className='container'>
              <p> {user.username}</p>
              <p>{user.userProfileId}</p>
            </div>
          </section>
        );
      })}
    </main>
  );
}

export default App;
