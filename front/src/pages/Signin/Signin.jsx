import axios from 'axios';
import React, { useState } from 'react';

function Signin(props) {
    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: ""
    });

    const handleInputChange = (e) => {
        setSigninInput({
            ...signinInput,
            [e.target.name]: e.target.value
        })
    };

    // async , await 사용시 try , catch 무조건 사용해주기
    const handleSigninButton = async () => {
        try {
            const response = await axios.post("http://localhost:8080/servlet_study_jiyoung/auth/signin", signinInput);
            
            console.log(response);

            if(!response.data.token) {    // token이 없으면 로그인실패
                alert("로그인 실패");
                return;
            }
            
            localStorage.setItem("token", response.data?.token);
            alert("환영합니다.");

        } catch(error) {
            console.log(error);
        }
    };

    return (
        <>
            <h1>로그인</h1>
            <div><input type="text" name='username' onChange={handleInputChange} placeholder='username'/></div>
            <div><input type="password" name='password' onChange={handleInputChange} placeholder='password'/></div>
            <div><button onClick={handleSigninButton}>로그인</button></div>
        </>
    )
}

export default Signin;