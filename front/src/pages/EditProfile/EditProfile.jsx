import React, { useEffect, useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';

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

function EditProfile(props) {
    const navigate = useNavigate();
    const [ profile, setProfile ] = useState();

    // 로그인 토큰 가져오기
    useEffect(() => {
        const getProfile = async() => {
            try {
                const response = await axios.get(`http://localhost:8080/servlet_study_jiyoung/mypage/profile`, {    // 어느서버에 요청을 날릴건지
                    headers: {
                        Authorization: localStorage.getItem("token")
                    }   // headers에 Authorization: 토큰을 요청날림
                });
                setProfile(response.data)
            }catch(error) {
                console.log(error);
            }
        }
        getProfile();
    }, []); // [] 안넣어주면 계속 요청날라감,,,지옥

    const handleInputChange = (e) => {
        setProfile({
            ...profile, // profile입력필드의 변화를 감지하여 [e.target.name]: e.target.value <- 요 값들을 Profile에 넣는거임
            [e.target.name]: e.target.value
        });
    }

    const handleUpdateClick = () => {

        const submit = async () => {
            const option = {
                headers: {  // headers에 Authorization: 토큰을 요청날림
                    Authorization: localStorage.getItem("token")
                }
            }
            // /mypage/profile 서버에 변화를 감지하여 집어넣은 profile(name:value) 와 headers에 Authorization: 토큰을 서버에 보내서저장
            const response = await axios.put("http://localhost:8080/servlet_study_jiyoung/mypage/profile", profile, option);
            if(response.data) {
                alert("수정완료");
                navigate("/mypage");
                return;
            }
        }
        submit();
    }

    return (
        <>
            <h1>회원정보 수정</h1>
            <div css={SInputLayout}>
                <input type="text" name="username" placeholder='username' onChange={handleInputChange} defaultValue={profile?.username}/>
            </div>
            <div css={SInputLayout}>
                <input type="password" name="password" placeholder='password' onChange={handleInputChange} defaultValue={profile?.password}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name="name" placeholder='name' onChange={handleInputChange} defaultValue={profile?.name}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name="email" placeholder='email' onChange={handleInputChange} defaultValue={profile?.email}/>
            </div>
            <div>
                <button onClick={handleUpdateClick}>수정하기</button>
            </div>
        </>
    )
}
export default EditProfile;