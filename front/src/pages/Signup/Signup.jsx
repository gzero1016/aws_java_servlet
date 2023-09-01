import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const SInputLayout = css`
    margin-bottom: 15px;
    width: 60%;
    height: 40px;

    & > input {
        width: 100%;
        height: 100%;
        text-align: center;
    }
`;

function Signup(props) {
    const navigate = useNavigate(); // 라우트이동하는 함수

    const [ signupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    const handleInputChange = (e) => {
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        })
    }

    const handleSubmitClick = () => {
        //회원가입 요청
        const option = {
            params: {
                username: signupUser.username
            }
        }
        // axios의 좋은점은 해당 객체를 자동으로 JSON으로 변경해준다.
        // 클라이언트가 서버한테 넘겨주는 요청값
        axios.get("http://localhost:8080/servlet_study_jiyoung/auth/signup/duplicate/username", option)
        // 서버가 클라이언트에게 넘겨주는 응답
        .then((response) => {
            // 중복확인 후 중복이 아닐경우 회원가입
            axios.post("http://localhost:8080/servlet_study_jiyoung/auth/signup", signupUser)
            .then((response) => {
                alert(response.data);   // 응답
                navigate("/signin");    // 이동하고자하는 경로
            })
        }).catch((error) => {
            alert("중복된 아이디입니다.");  // 중복응답
        })
    }

    return (
        <>
            <h1>회원가입</h1>
            <div css={SInputLayout}>
                <input type="text" name="username" placeholder='username' onChange={handleInputChange} />
            </div>
            <div css={SInputLayout}>
                <input type="password" name="password" placeholder='password' onChange={handleInputChange} />
            </div>
            <div css={SInputLayout}>
                <input type="text" name="name" placeholder='name' onChange={handleInputChange} />
            </div>
            <div css={SInputLayout}>
                <input type="text" name="email" placeholder='email' onChange={handleInputChange} />
            </div>
            <div>
                <button onClick={handleSubmitClick}>가입하기</button>
            </div>
        </>
    );
}

export default Signup;