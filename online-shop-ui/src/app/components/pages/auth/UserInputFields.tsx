   

import React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import InputFields from '../../../utils/InputFields';
import { InputError, getFieldInputErrorMessage, isFieldPresentInInputErrors } from '../../../utils/InputErrorUtils';
import { useAppSelector } from '../../../hooks';
import { getTranslation } from '../../../../i18n/i18n';

interface UserInputProps {
    title: string,
    buttonText: string,
    handleSubmit: Function,
    isEmailAndRoleMandatory: boolean,
    inputErrors: InputError[]
}

const UserInputFields = ({ title, buttonText, handleSubmit, isEmailAndRoleMandatory, inputErrors }: UserInputProps) => {
    const [role, setRole] = React.useState('USER');
    const lang = useAppSelector(state => state.lang.lang);

    const handleRoleChange = (event: SelectChangeEvent) => {
        setRole(event.target.value);
    };

    return (
        <React.Fragment>
            <CssBaseline />
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                    <LockOutlinedIcon />
                </Avatar>
                <Typography component="h1" variant="h5" data-testid="title">
                    {title}
                </Typography>
                <Box component="form" onSubmit={(e) => handleSubmit(e)} noValidate sx={{ mt: 1 }}>
                    {isEmailAndRoleMandatory &&
                        <>
                            <FormControl fullWidth>
                                <InputLabel id="roleInput">Role</InputLabel>
                                <Select
                                    labelId="role"
                                    id="role"
                                    data-testid="role"
                                    value={role}
                                    label={getTranslation(lang, "role")}
                                    onChange={handleRoleChange}
                                >
                                    <MenuItem value={"USER"} id="user-role">{getTranslation(lang, "user")}</MenuItem>
                                    <MenuItem value={"SELLER"} id="seller-role">{getTranslation(lang, "seller")}</MenuItem>
                                </Select>
                            </FormControl>
                            <TextField
                                error={isFieldPresentInInputErrors(InputFields.EMAIL, inputErrors)}
                                helperText={isFieldPresentInInputErrors(InputFields.EMAIL, inputErrors) ? getFieldInputErrorMessage(InputFields.EMAIL, inputErrors, lang) : null}
                                margin="normal"
                                required
                                fullWidth
                                name="email"
                                label={getTranslation(lang, "email")}
                                type="email"
                                id="email"
                                data-testid="email"
                                autoComplete="current-email"
                            />
                        </>}
                    <TextField
                        error={isFieldPresentInInputErrors(InputFields.USERNAME, inputErrors)}
                        helperText={isFieldPresentInInputErrors(InputFields.USERNAME, inputErrors) ? getFieldInputErrorMessage(InputFields.USERNAME, inputErrors, lang) : null}
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        data-testid="username"
                        label={getTranslation(lang, "username")}
                        name="username"
                        autoComplete="username"
                        autoFocus
                    />
                    <TextField
                        error={isFieldPresentInInputErrors(InputFields.PASSWORD, inputErrors)}
                        helperText={isFieldPresentInInputErrors(InputFields.PASSWORD, inputErrors) ? getFieldInputErrorMessage(InputFields.PASSWORD, inputErrors, lang) : null}
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label={getTranslation(lang, "password")}
                        type="password"
                        id="password"
                        data-testid="password"
                        autoComplete="current-password"
                    />
                    <Button
                        type="submit"
                        data-testid="submit-button"
                        fullWidth
                        variant="contained"
                        id="submit"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        {buttonText}
                    </Button>
                </Box>
            </Box>
        </React.Fragment>);
}

export default UserInputFields;