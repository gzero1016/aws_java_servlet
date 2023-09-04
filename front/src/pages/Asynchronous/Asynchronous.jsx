import React from 'react';

function Asynchronous(props) {

    /*  
        동기(Synchronous): 순서대로 동작
        비동기(ASynchronous): 순서대로 동작하지 않음

        콜백(함수)을 사용하는 이유?
        비동기처리 안에서 지정한 순서대로 함수 호출 및 처리될 수 있도록 하는것
    */

    let num = 0;

    const handleClick = () => {
        num++;

        const click1 = (num) => {
            console.log(`${num} click1!!!`);
        }
        const click1After = () => {
            console.log("click1 다음 실행!");
        }
        const click2 = (num) => {
            console.log(`${num} click2!!!`);
        }
        const click2After = () => {
            console.log("click2 다음 실행!");
        }

        const clickFx = (fx1, fx2) => {
            fx1(num);
            fx2();
        }
        // setTimeout: 비동기 처리함수, 몇초후에 실행할수 있도록 해주는거
        setTimeout(clickFx, Math.random(3) * 1000, click1, click1After);   // 1000에 1초
        setTimeout(clickFx, Math.random(3) * 1000, click2, click2After);   // 1000에 1초
    };

    // Promise 기본 구조 (무조건 매개변수 resolce, reject를 받는다.)
    const handleClick2 = () => {
        const p1 = new Promise((resolve, reject) => {
            const num = Math.random(4);
            if(Math.round(num % 2, 0) === 0) {
                resolve("짝수");    // resolve: then으로 들어가는것
            }else {
                reject(new Error("홀수"));  // reject: catch로 들어가는것
            }
        });
        p1 // Promise 실행되고 난 후에 resolve가 result로 들어간다.
        .then(result => {
            console.log(result);
            return "첫번째 then의 리턴";
        // 위 then이 끝남과 동시에 "첫번째 then의 리턴" 이 다음 then으로 들어간다
        }).then(result => {
            console.log(result);
        }).catch (error => {
            console.log(error);
        });
    }

    const handleClick3 = () => {
        // Promise 방법
        const printUser2 = () => {
            return new Promise((resolve, reject) => {
                resolve("유저2");
                reject(new Error("오류2"));
            });
        }
        printUser2().then(r => console.log(r));

        // async 방법
        const printUser = async () => {
            try {
                await printUser2().then((r) => {
                    console.log(r);
                });
                throw new Error("오류 처리");
            } catch(error) {
                console.log(error);
            }
            return "유저1";
        }
        printUser().then(r => console.log(r));

    }

    return (
        <div>
            <button onClick={handleClick}>클릭1</button>
            <button onClick={handleClick2}>클릭2</button>
            <button onClick={handleClick3}>클릭3</button>
        </div>
    ); 
}

export default Asynchronous;