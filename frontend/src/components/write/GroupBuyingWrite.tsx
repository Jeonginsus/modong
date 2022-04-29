import React, { useState } from 'react';
import { Link } from 'react-router-dom';

import "../../style/_groupBuyingWrite.scss"

import TextField from '@mui/material/TextField';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';

function GroupBuyingWrite() {
    const [productName, setProductName] =useState<string>("");
    const [productURL, setProductURL] =useState<string>("");
    const [productCost, setProductCost] =useState<string>("");
    const [GroupBuyingTime, setGroupBuyingTime] = useState<Date>(null);
    const handleURLchange= (e:React.ChangeEvent<HTMLTextAreaElement>)=>{
        setProductURL(e.target.value);
    }
    const handleNamechange= (e:React.ChangeEvent<HTMLTextAreaElement>)=>{
        setProductName(e.target.value);
    }
    const handleCostchange= (e:React.ChangeEvent<HTMLTextAreaElement>)=>{
        setProductCost(e.target.value);
    }
    return (
        <div className='gboutLine'>
            <TextField fullWidth
            margin="normal"
            id="outlined-name"
            value={productURL||""}
            label="URL주소"
            onChange={handleURLchange}
            />
            <TextField fullWidth
            margin="normal"
            id="outlined-name"
            value={productName||""}
            label="물품명"
            onChange={handleNamechange}
            />
            <TextField fullWidth
            margin="normal"
            id="outlined-name"
            value={productCost||""}
            label="가격"
            onChange={handleCostchange}
            />
            <LocalizationProvider dateAdapter={AdapterDateFns}>
                <TimePicker
                    label="마감시간"
                    value={GroupBuyingTime||null}
                    onChange={(newValue) => {
                        setGroupBuyingTime(newValue);
                    }}
                    renderInput={(params) => <TextField margin="normal" fullWidth {...params} />}
                />
            </LocalizationProvider>
            <a href={productURL}> 이 주소가 맞나요?</a>
            <Link className='button' to="/">생성하기</Link>
        </div>
    );
}

export default GroupBuyingWrite;