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

        // 비동기 안에서 순서대로 실행하고자할때 await을 활용하면 편하게 비동기안에서 동기적으로 사용할 수 있다. (동기적)
        const signup = async () => {
            let response = await axios.get("http://localhost:8080/servlet_study_jiyoung/auth/signup/duplicate/username", option);

            if(response.data) {
                alert("중복된 아이디입니다.");
                return;
            }

            try {
                response = await axios.post("http://localhost:8080/servlet_study_jiyoung/auth/signup", signupUser);
                if(!response.data) {
                    throw new Error(response);
                }
                alert("회원가입 성공!");
                navigate("/signin");
            }catch(error) {
                console.log(error);
            }
        };

        // 위 랑 같은 코드를 비동기
        // // axios의 좋은점은 해당 객체를 자동으로 JSON으로 변경해준다.
        // // 클라이언트가 서버한테 넘겨주는 요청값
        // axios.get("http://localhost:8080/servlet_study_jiyoung/auth/signup/duplicate/username", option)
        // // 서버가 클라이언트에게 넘겨주는 응답
        // .then((response) => {
        //     console.log(response);
        //     // data는 서버에서 받아온 body부분
        //     const isDuplicateUsername = response.data //(false가 들어감)
        //     if(isDuplicateUsername) {
        //         // 아이디가 중복되었을때
        //     } else {
        //         // 중복되지않고 사용가능한 아이디일때
        //     }
        // }).catch((error) => {

        // })

        signup();
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